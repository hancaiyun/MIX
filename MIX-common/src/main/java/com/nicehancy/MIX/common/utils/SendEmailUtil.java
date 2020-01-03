package com.nicehancy.MIX.common.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/11/21 15:10
 **/
@Slf4j
public class SendEmailUtil {

    /**
     * 私有构造函数
     */
    private SendEmailUtil(){
    }

    /**
     * 发送邮件
     * @param subject           邮件主题
     * @param content           邮件内容
     * @param reciter           接受者
     */
    public static void sendEmail(String subject, String content, String reciter) {

        //创建一个配置文件并保存
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.qq.com");
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","true");


        //QQ存在一个特性设置SSL加密
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //创建一个session对象, 邮件发送者与授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("nicehcy@foxmail.com", "qzolesbimzrjbdci");
                }
            });

            //开启debug模式
            session.setDebug(true);

            //获取连接对象
            Transport transport = session.getTransport();

            //连接服务器
            transport.connect("smtp.qq.com", "nicehcy@foxmail.com", "qzolesbimzrjbdci");

            //创建邮件对象
            MimeMessage mimeMessage = new MimeMessage(session);

            //邮件发送人
            mimeMessage.setFrom(new InternetAddress("nicehcy@foxmail.com"));

            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(reciter));

            //邮件标题
            mimeMessage.setSubject(subject);

            //邮件内容
            mimeMessage.setContent(content, "text/html;charset=UTF-8");

            //发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            //关闭连接
            transport.close();
        }catch (Exception e){
            log.error("发送邮件失败，失败信息：{}, 待发送的邮件信息：收件人邮箱={}， 邮件主题={},邮件内容={}", e, reciter,subject,content);
        }
    }
}
