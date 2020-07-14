$("#commit").click(function(){
    const content = document.getElementById("suggestion").value;
    if(content == null || content ===''){
        layer.msg('反馈内容不能为空', {icon: 5});
        return;
    }
    $.ajax({
        url: '/suggest/commit',
        data: {"content":content, "userNo":window.localStorage["loginNo"]},
        dataType: 'json',//数据类型
        type: 'GET',//类型
        timeout: 3000,//超时
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                //关闭弹窗
                layer.closeAll();
                //提交结果
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