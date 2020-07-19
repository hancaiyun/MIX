//初始化一级目录
//缓存中获取用户ID
window.onload = function () {
    //请求查询
    $.ajax({
        url: '/note/notemanage/queryDirectory',
        data: {"userNo": window.localStorage["loginNo"]},
        dataType: 'json',//数据类型
        type: 'GET',//类型
        timeout: 3000,//超时
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                var directoryList = res.data;
                $.each(directoryList, function (index, item) {
                    //alert("参数打印：" + index + ";" + item);
                    $("#primaryDirectory").append(new Option(item, item));
                });
                //重载select模块，否则不会展示
                layui.form.render("select");
            } else {
                layer.open({
                    title: '提示信息'
                    , content: '笔记目录查询失败，失败原因：' + res.data.msg
                });
            }
        },
        //失败/超时
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (textStatus === 'timeout') {
                layer.error('网络异常');
                setTimeout(function () {
                    layer.msg('正在重新请求');
                }, 3000);
            }
            layer.error("失败原因：" + errorThrown);
        }
    });
};

//初始化富文本域
//富文本集相关操作
layui.use('layedit', function () {
    const layedit = layui.layedit;
    const $ = layui.jquery;
    // var layer = layui.layer;
    //建立编辑器
    const textarea = layedit.build('note-area', {height: 480, uploadImage: {url: '/file/upload', type: 'post'}});
    //放入缓存，用于之后的更新操作
    layui.data('session', {
        key: 'textarea',
        value: textarea
    });

    //保存文件，快捷键监听
    document.addEventListener('keydown', function(e) {
        if (e.keyCode === 83 && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey))      {
            e.preventDefault();
            $('#save').click();
        }
    });


    //保存文件
    $("#save").click(function () {

        //获取文件名
        const fileName = document.getElementById("fileName").value;
        //获取一级目录名
        const primaryDirectory = document.getElementById("primaryDirectory").value;
        //获取二级目录名
        const secondaryDirectory = document.getElementById("secondaryDirectory").value;
        //保存文件需指定文件名
        if (fileName === "" || fileName == null) {
            layer.msg('请选择要保存的文件', {icon: 5});
            return;
        }
        //获取文本域内容
        const content = layedit.getContent(textarea);
        $.ajax({
            url: '/note/notemanage/save',//提交地址
            data: {
                "userNo": window.localStorage["loginNo"],
                "primaryDirectory": primaryDirectory,
                "secondaryDirectory": secondaryDirectory,
                "fileName": fileName,
                "content": content
            },//数据， id获取
            dataType: 'json',//数据类型-json
            type: 'GET',//类型
            timeout: 3000,//超时
            //请求成功
            success: function (res) {
                if (res.code === '0000') {
                    layer.msg("保存成功");
                } else {
                    layer.error({
                        title: '失败信息'
                        , content: res.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    alert('請求超時');
                    setTimeout(function () {
                        alert('重新请求');
                    }, 3000);
                }
                layui.error(errorThrown);
            }
        })
    });
});


//目录集操作
layui.use(['form', 'layer', 'layedit'], function () {
    const $ = layui.jquery;
    const form = layui.form;
    const layer = layui.layer;
    const layedit = layui.layedit;

    //一级目录选中触发事件——查询二级目录列表
    form.on('select(primaryDirectory)', function (data) {

        //1、获取data.value得到被选中的值
        const primaryDirectory = data.value;
        if (primaryDirectory === "" || primaryDirectory == null) {
            return;
        }

        //2、查询一级目录下面的二级目录以及文档列表
        //2.1 查询二级目录列表并append option
        //请求查询
        $.ajax({
            url: '/note/notemanage/queryDirectory',
            data: {"userNo": window.localStorage["loginNo"], "primaryDirectory": primaryDirectory},
            dataType: 'json',//数据类型
            type: 'GET',//类型
            timeout: 3000,//超时
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    const directoryList = res.data;
                    //清空原数据
                    removeAll("secondaryDirectory");
                    $.each(directoryList, function (index, item) {
                        $("#secondaryDirectory").append(new Option(item, item));
                    });
                    //重载select模块，否则不会展示
                    layui.form.render("select");
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '笔记目录查询失败，失败原因：' + res.data.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    layer.error('网络异常');
                    setTimeout(function () {
                        layer.msg('正在重新请求');
                    }, 3000);
                }
                layer.error("失败原因：" + errorThrown);
            }
        });

        //2.2 查询一级目录下的笔记列表
        //请求查询
        $.ajax({
            url: '/note/notemanage/queryFileList',
            data: {
                "userNo": window.localStorage["loginNo"],
                "primaryDirectory": primaryDirectory
            },
            dataType: 'json',//数据类型
            type: 'GET',//类型
            timeout: 3000,//超时
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    var directoryList = res.data;
                    //清空原数据
                    removeAll("fileName");
                    $.each(directoryList, function (index, item) {
                        $("#fileName").append(new Option(item, item));
                    });
                    //重载select模块，否则不会展示
                    layui.form.render("select");
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '笔记目录查询失败，失败原因：' + res.data.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    layer.error('网络异常');
                    setTimeout(function () {
                        layer.msg('正在重新请求');
                    }, 3000);
                }
                layer.error("失败原因：" + errorThrown);
            }
        });
    });

    //二级目录选中触发事件——查询文件列表
    form.on('select(secondaryDirectory)', function (data) {

        //data.value 得到被选中的值
        const secondaryDirectory = data.value;
        if (secondaryDirectory === "" || secondaryDirectory == null) {
            //清空文件列表  TODO 非清空文件列表，而是触发根据一级目录再查询一次
            removeAll("fileName");
            layui.form.render("select");
            return;
        }
        //获取一级目录选中的值
        const primaryDirectory = document.getElementById("primaryDirectory").value;
        //清空富文本集内容

        //查询文件名列表并append option
        //请求查询
        $.ajax({
            url: '/note/notemanage/queryDirectory',
            data: {
                "userNo": window.localStorage["loginNo"],
                "primaryDirectory": primaryDirectory,
                "secondaryDirectory": secondaryDirectory
            },
            dataType: 'json',//数据类型
            type: 'GET',//类型
            timeout: 3000,//超时
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    var directoryList = res.data;
                    //清空原数据
                    removeAll("fileName");
                    $.each(directoryList, function (index, item) {
                        $("#fileName").append(new Option(item, item));
                    });
                    //重载select模块，否则不会展示
                    layui.form.render("select");
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '笔记目录查询失败，失败原因：' + res.data.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    layer.error('网络异常');
                    setTimeout(function () {
                        layer.msg('正在重新请求');
                    }, 3000);
                }
                layer.error("失败原因：" + errorThrown);
            }
        });

    });

    //文件列表点击触发事件——查询文件内容并同步富文本集
    form.on('select(fileName)', function (data) {

        //data.value 得到被选中的值
        const fileName = data.value;
        //获取一级目录名
        const primaryDirectory = document.getElementById("primaryDirectory").value;
        //获取二级目录名
        const secondaryDirectory = document.getElementById("secondaryDirectory").value;
        //清空富文本集内容

        //查询文件内容
        $.ajax({
            url: '/note/notemanage/queryNoteInfo',
            data: {
                "userNo": window.localStorage["loginNo"],
                "primaryDirectory": primaryDirectory,
                "secondaryDirectory": secondaryDirectory,
                "fileName": fileName
            },
            dataType: 'json',//数据类型
            type: 'GET',//类型
            timeout: 3000,//超时
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    const noteContent = res.data;
                    const session = layui.data("session");
                    layedit.setContent(session.textarea, noteContent);
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '笔记查询失败，失败原因：' + res.data.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    layer.error('网络异常');
                    setTimeout(function () {
                        layer.msg('正在重新请求');
                    }, 3000);
                }
                layer.error("失败原因：" + errorThrown);
            }
        });
    });
});


//删除select中的option——用于清空历史append
function removeAll(selectId) {
    const obj = document.getElementById(selectId);
    obj.options.length = 1;
}


//管理文件
function manageFile() {
    layer.open({
        type: 2
        , title: '笔记管理'
        , content: 'noteform'
        , area: ['460px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            const iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'LAY-user-front-submit'
                , submit = layero.find('iframe').contents().find('#' + submitID);

            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                const $ = layui.jquery;
                const field = data.field; //获取提交的字段
                //var jsonData = JSON.stringify(field);// 转成JSON格式
                //管理请求
                $.ajax({
                    url: '/note/notemanage/manage',
                    data: {
                        "primaryDirectory": field.primaryDirectory,
                        "secondaryDirectory": field.secondaryDirectory,
                        "documentName": field.fileName,
                        "operateType": field.operateType,
                        "userNo": window.localStorage["loginNo"]
                    },
                    dataType: 'json',//数据类型
                    type: 'GET',//请求方式
                    timeout: 3000,//超时时间
                    //请求成功
                    success: function (res) {
                        if (res.code === "0000") {
                            //展示主页
                            layer.msg("操作成功");
                            layui.form.render("select");
                            //window.location.href="/index";
                        } else {
                            layer.error({
                                title: '提示信息'
                                , content: '笔记创建或者删除失败，失败原因：' + res.msg
                            });
                        }
                    },
                    //失败/超时
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (textStatus === 'timeout') {
                            layer.error('网络异常');
                            setTimeout(function () {
                                alert('重新请求');
                            }, 3000);
                        }
                        layer.error(errorThrown);
                    }
                });
                layer.close(index); //关闭弹层
            });
            submit.trigger('click');
        }
    });
}

//删除文件
function deleteFile() {

    //获取用户名
    const userNo = window.localStorage["loginNo"];
    //获取一级目录名
    const primaryDirectory = document.getElementById("primaryDirectory").value;
    //获取二级目录名
    const secondaryDirectory = document.getElementById("secondaryDirectory").value;
    //获取文件名
    const fileName = document.getElementById("fileName").value;

    //文件名为空， 弹出提示信息
    if(fileName === '' || fileName == null){
        layer.open({
            title: '删除失败'
            , content: '未选中待删除文档！'
        });
        return;
    }
    //删除之前弹出提示信息
    layer.open({
        title: '文档删除提示信息'
        , btn: ['确定']
        , content: '确认删除此条文档？删除后无法恢复'
        ,yes:function () {

            //请求密码重置
            $.ajax({
                url: '/note/delete',
                data:{
                    "primaryDirectory": primaryDirectory,
                    "secondaryDirectory": secondaryDirectory,
                    "documentName": fileName,
                    "userNo": userNo},
                dataType: 'json',//数据类型
                type: 'GET',//请求方式
                timeout: 3000,//超时时间
                //请求成功
                success: function (res) {
                    if (res.code === "0000") {
                        //展示主页
                        layer.open({
                            title: '提示信息'
                            , content: '删除成功'
                        });
                    } else {
                        layer.open({
                            title: '提示信息'
                            , content: '文档删除失败，失败原因：' + res.msg
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
}