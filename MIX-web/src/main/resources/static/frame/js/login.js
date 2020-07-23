//本地缓存用户信息， 输入密码后输入验证码时，触发缓存
function storage() {
    const storage = window.localStorage;
    storage["loginNo"] = document.getElementsByName("username")[0].value;
}

layui.config({
    base: '../../frame/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'user'], function () {
    const form = layui.form;
    form.render();
});

const show_num = [];
$(function(){
    draw(show_num);
    //点击验证码重绘
    $("#canvas").on('click',function(){
        draw(show_num);
    });
})

//验证码校验事件
function checkVerCode() {
    const val = document.getElementsByName("vercode")[0].value.toLowerCase();
    const num = show_num.join("");
    //为空时优先校验非空，不做正确性校验
    if (val == null || val === '') {
        return;
    }
    if (val !== num) {
        draw(show_num);
        document.getElementById("message").innerText = "验证码错误";
        return false;
    }
}

//绘图
function draw(show_num) {
    let i;
    let y;
    let x;
    const canvas_width = $('#canvas').width();
    const canvas_height = $('#canvas').height();
    const canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    const context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    const sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    const aCode = sCode.split(",");
    const aLength = aCode.length;//获取到数组的长度

    for (i = 0; i <= 3; i++) {
        const j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        const deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        const txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        x = 10 + i * 20;//文字在canvas上的x坐标
        y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";

        context.translate(x, y);
        context.rotate(deg);

        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);

        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        x = Math.random() * canvas_width;
        y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

//设置颜色
function randomColor() {//得到随机的颜色值
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}

//忘记密码
function forgetPS(userNo) {
    layui.use('layer', function () {
        // 操作对象
        const layer = layui.layer;
        const $ = layui.jquery;
        //弹出发送密码重置邮件的弹窗
        parent.layer.open({
            title: '重置密码提示信息'
            , btn: ['确定']
            , content: '确认重置密码？ 确认重置后，密码将已邮件的方式发送到您的邮箱'
            ,yes:function () {
                //alert("用户名:" + userNo);
                if(userNo == null || userNo ===''){
                    return;
                }
                //请求密码重置
                $.ajax({
                    url: '/user/resetPassword',
                    data:{"userNo": userNo},
                    dataType: 'json',//数据类型
                    type: 'GET',//请求方式
                    timeout: 3000,//超时时间
                    //请求成功
                    success: function (res) {
                        if (res.code === "0000") {
                            //展示主页
                            layer.open({
                                title: '提示信息'
                                , content: '密码已重置，请稍后在邮箱' + res.data.email + '中查看密码'
                            });
                        } else {
                            layer.open({
                                title: '提示信息'
                                , content: '密码重置失败，失败原因：' + res.msg
                            });
                        }
                    },
                    //失败/超时
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (textStatus === 'timeout') {
                            alert('网络异常');
                            setTimeout(function () {
                            }, 30000);
                        }
                        alert(errorThrown);
                    }
                });
            }
        });
    });
}