<!-- 消息补发脚本 -->
layui.define(['form', 'upload'], function () {
    const   $ = layui.$
        ,layer = layui.layer
        , form = layui.form;

    //发送
    form.on('submit(send)', function (obj) {
        //1、业务类型
        const businessType = document.getElementsByName("businessType")[0].value;
        if(businessType !== 'PASSWORD_RESET' && businessType !== 'USER_REGISTER'){
            return layer.msg("请选择业务类型");
        }
        //2、收件人
        const recipient = document.getElementsByName("recipient")[0].value;
        //格式校验，手机号或者邮箱
        //邮箱正则格式
        const mailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        //手机号正则格式
        const phoneReg = /^1[3456789]\d{9}$/;
        if(!mailReg.test(recipient) && !phoneReg.test(recipient)){
            return layer.msg("收件人格式有误");
        }
        const data = obj.field;
        data.userNo = window.localStorage["loginNo"];
        //提交修改

        $.ajax({
            url: '/message/reSend',
            data: data,
            dataType: 'json',//数据类型
            type: 'GET',//类型
            timeout: 3000,//超时
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    //获取头像地址、昵称
                    layer.msg("补发成功");
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '消息补发失败，失败原因：' + res.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    layer.msg('网络异常');
                }
                layer.msg("失败原因：" + errorThrown);
            }
        });
        return true;
    });
});