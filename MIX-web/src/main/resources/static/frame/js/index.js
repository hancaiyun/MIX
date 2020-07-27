layui.config({
    base: '/frame/'//静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use('index');

<!-- 本地缓存中获取用户名并设置到指定位置 -->
window.onload=function(){
    let headCopy = "";
    //查询用户信息，包括图片地址信息，用于设置头像（设置默认头像图片，固定图片地址）
    $.ajax({
        url: '/set/user/info',
        data: {"userNo": window.localStorage["loginNo"]},
        dataType: 'json',//数据类型
        type: 'GET',//类型
        timeout: 3000,//超时
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                //获取头像地址、昵称
                headCopy = res.data.headCopy;
                //设置头像
                setHeadCopy(headCopy);
                //根据权限编号决定显示管理组模块
                setColumn(res.data.authCode);
            } else {
                layer.open({
                    title: '提示信息'
                    , content: '用户信息查询失败，失败原因：' + res.msg
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
};

//设置头像与用户名
function setHeadCopy(headCopy){
    if(headCopy === "" || headCopy == null){
        //默认头像地址
        headCopy = "default.png";
    }

    //设置用户展示信息
    const storage = window.localStorage;//拼接用户前缀图标
    const maskName = mobilePhoneMask(storage["loginNo"]);
    document.getElementById("userNo").innerHTML = "<cite><img src=\"/file/download?fileName="+ headCopy +"\" " +
        "class=\"layui-circle\" picture\" id=\"picture\" width=\"25\" height=\"25\"> " + maskName + "</cite>";
}

//手机号掩码处理
function mobilePhoneMask(input) {
    let output = input;
    if (input == null || input === "")return output;
    output = input.substr(0, 3) + "****" + input.substr(input.length - 4);
    return output;
}

//额外权限组模块展示
function setColumn(authCode){
    //超级管理员
    if('SUPPER_ADMIN' === authCode){
        document.getElementById("user").style.display="block";
        document.getElementById("auth").style.display="block";
        document.getElementById("system").style.display="block";
        document.getElementById("message").style.display="block";
    }

    //普通管理员
    if('ADMIN' === authCode){
        document.getElementById("system").style.display="block";
        document.getElementById("message").style.display="block";
    }

    //普通用户展示默认
    //TODO 用户等级的不同操作权限
}