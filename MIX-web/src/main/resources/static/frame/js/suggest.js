<!-- 建议提交脚本 -->
layui.define(['form'], function () {
    const $ = layui.$
         ,layer = layui.layer;

    $.ajax({
        url: '/suggest/commit',
        data: {"content":document.getElementById("suggest").value, "userNo":window.localStorage["loginNo"]},
        dataType: 'json',//数据类型
        type: 'GET',//类型
        timeout: 3000,//超时
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                //获取头像地址、昵称
                layer.msg("感谢您的反馈");
            } else {
                layer.open({
                    title: '提示信息'
                    , content: '反馈失败，失败原因：' + res.msg
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
});