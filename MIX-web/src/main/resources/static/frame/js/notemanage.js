//初始化一级目录
//缓存中获取用户ID
window.onload = function () {
    //请求查询
    $.ajax({
        url: '/note/queryDirectory',
        data: {"userNo": window.localStorage["loginNo"]},
        dataType: 'json',//数据类型
        type: 'GET',//类型
        timeout: 3000,//超时
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                const directoryList = res.data;
                $.each(directoryList, function (index, item) {
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
    //建立编辑器
    const textarea = layedit.build('note-area', {height: 480, uploadImage: {url: '/file/uploadPic', type: 'post'}});
    //放入缓存，用于之后的更新操作
    layui.data('session', {
        key: 'textarea',
        value: textarea
    });

    //保存文件，快捷键监听ctrl+s
    document.addEventListener('keydown', function (e) {
        if (e.keyCode === 83 && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)) {
            e.preventDefault();
            $('#save').click();
        }
    });

    //TODO table键监听
    document.addEventListener('keydown', function (e) {
        if (e.keyCode === 9) {
            alert("已监听到");
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                window.event.returnValue = false;
            }
            document.getElementById("note-area").value += "    ";
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
            url: '/note/save',//提交地址
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

    //共享文件
    $("#share").click(function () {

        //获取文件名
        const fileName = document.getElementById("fileName").value;
        //获取一级目录名
        const primaryDirectory = document.getElementById("primaryDirectory").value;
        //获取二级目录名
        const secondaryDirectory = document.getElementById("secondaryDirectory").value;

        //按钮禁用判断-返回
        if(document.getElementById("share").classList.contains("layui-btn-disabled")){
            if (fileName !== "" && fileName != null) {
                layer.tips('已发布的可在共享社区中查看', this);
            }
            return;
        }
        if(fileName === "" || fileName == null){
            layer.msg('请选择要发布的文件', {icon: 5});
            return;
        }

        //弹窗-确认
        layer.confirm('确认发布笔记？发布之后别人将可以看到您的笔记', function (index) {
            //确认发布
            $.ajax({
                url: '/note/share',//提交地址
                data: {
                    "userNo": window.localStorage["loginNo"],
                    "primaryDirectory": primaryDirectory,
                    "secondaryDirectory": secondaryDirectory,
                    "fileName": fileName
                },//数据， id获取
                dataType: 'json',//数据类型-json
                type: 'GET',//类型
                timeout: 3000,//超时
                //请求成功
                success: function (res) {
                    if (res.code === '0000') {
                        //分享按钮变更
                        document.getElementById("share").classList.add("layui-btn-disabled");
                        layer.msg("发布成功");
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
            //成功关闭弹窗
            layer.close(index);
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
            url: '/note/queryDirectory',
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
            url: '/note/queryFileList',
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
                    const directoryList = res.data;
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
        if (secondaryDirectory === "") {
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
            url: '/note/queryDirectory',
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
                    const directoryList = res.data;
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
            url: '/note/queryNoteInfo',
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
                    //设置分享按钮
                    if (res.data.shareFlag !== 'TRUE') {
                        document.getElementById("share").classList.remove("layui-btn-disabled");
                    }
                    //设置富文本集内容
                    const noteContent = res.data.content;
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

<!--操作组-->
//获取用户名
const userNo = window.localStorage["loginNo"];

//新增
function addNote() {
    //获取选中的操作位置,新增位置校验
    const opLocation = document.getElementById("opLocation").value;
    if (opLocation === '') {
        layer.msg('请选择要新增的位置', {icon: 5});
        return;
    }

    //获取一级目录名
    const primaryDirectory = document.getElementById("primaryDirectory").value;

    //操作组选中
    //选中一级目录
    if (opLocation === 'PRIMARY_DIRECTORY_OP') {
        choosePromptForAdd("PRIMARY_DIRECTORY_OP");
    }
    //选中二级目录
    if (opLocation === 'SECONDARY_DIRECTORY_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择一级目录', {icon: 5});
        } else {
            choosePromptForAdd("SECONDARY_DIRECTORY_OP");
        }
    }

    //选中文件
    if (opLocation === 'FILE_OP') {
        if (primaryDirectory === '') {
            layer.msg('请至少选择一级目录', {icon: 5});
        } else {
            choosePromptForAdd("FILE_OP");
        }
    }
}

//修改（重命名）
function editNote() {
    //获取选中的操作位置
    const opLocation = document.getElementById("opLocation").value;
    if (opLocation === '') {
        layer.msg('请选择要修改的位置', {icon: 5});
        return;
    }

    //获取一级目录名
    const primaryDirectory = document.getElementById("primaryDirectory").value;
    const secondaryDirectory = document.getElementById("secondaryDirectory").value;
    const fileName = document.getElementById("fileName").value;

    //操作组选中
    //选中一级目录
    if (opLocation === 'PRIMARY_DIRECTORY_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择一级目录', {icon: 5});
        } else {
            choosePromptForEdit("PRIMARY_DIRECTORY_OP", primaryDirectory);
        }
    }
    //选中二级目录
    if (opLocation === 'SECONDARY_DIRECTORY_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择所属的一级目录', {icon: 5});
        } else if (secondaryDirectory === '') {
            layer.msg('请选择要修改的二级目录', {icon: 5});
        } else {
            choosePromptForEdit("SECONDARY_DIRECTORY_OP", secondaryDirectory);
        }
    }

    //选中文件
    if (opLocation === 'FILE_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择该文件所属的目录', {icon: 5});
        } else if (fileName === '') {
            layer.msg('请选择要修改的文件', {icon: 5});
        } else {
            choosePromptForEdit("FILE_OP", fileName);
        }
    }
}

//删除
function deleteNote() {
    //获取选中的操作位置
    const opLocation = document.getElementById("opLocation").value;
    if (opLocation === '') {
        layer.msg('请选择要删除的位置', {icon: 5});
        return;
    }

    //获取一级目录名
    const primaryDirectory = document.getElementById("primaryDirectory").value;
    const secondaryDirectory = document.getElementById("secondaryDirectory").value;
    const fileName = document.getElementById("fileName").value;

    //操作组选中
    //选中一级目录
    if (opLocation === 'PRIMARY_DIRECTORY_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择要删除的目录', {icon: 5});
        } else {
            deleteConfirm(opLocation, primaryDirectory);
        }
    }
    //选中二级目录
    if (opLocation === 'SECONDARY_DIRECTORY_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择所在的一级目录', {icon: 5});
        } else if (secondaryDirectory === '') {
            layer.msg('请选择要删除的二级目录', {icon: 5});
        } else {
            deleteConfirm(opLocation, secondaryDirectory);
        }
    }

    //选中文件
    if (opLocation === 'FILE_OP') {
        if (primaryDirectory === '') {
            layer.msg('请选择该文件所属的目录', {icon: 5});
        } else if (fileName === '') {
            layer.msg('请选择要删除的文件', {icon: 5});
        } else {
            deleteConfirm(opLocation, fileName);
        }
    }
}

//公共方法
//选择新增弹出框
function choosePromptForAdd(opLocation) {
    if (opLocation === 'PRIMARY_DIRECTORY_OP') {
        layer.prompt({title: '创建一级目录'}, function (value, index) {
            //新增请求
            doRequest('PRIMARY_DIRECTORY_OP', 'ADD', value, index);
        });
    }
    if (opLocation === 'SECONDARY_DIRECTORY_OP') {
        layer.prompt({title: '创建二级目录'}, function (value, index) {
            //新增请求
            doRequest('SECONDARY_DIRECTORY_OP', 'ADD', value, index);
        });
    }
    if (opLocation === 'FILE_OP') {
        layer.prompt({title: '创建文件'}, function (value, index) {
            //新增请求
            doRequest('FILE_OP', 'ADD', value, index);
        });
    }
}

//选择修改弹出框
function choosePromptForEdit(opLocation, originName) {
    if (opLocation === 'PRIMARY_DIRECTORY_OP') {
        layer.prompt({title: '修改一级目录名', value: originName}, function (value, index) {
            //修改请求
            doRequest('PRIMARY_DIRECTORY_OP', 'EDIT', value, index);
        });
    }
    if (opLocation === 'SECONDARY_DIRECTORY_OP') {
        layer.prompt({title: '修改二级目录名', value: originName}, function (value, index) {
            //修改请求
            doRequest('SECONDARY_DIRECTORY_OP', 'EDIT', value, index);
        });
    }
    if (opLocation === 'FILE_OP') {
        layer.prompt({title: '修改文件名', value: originName}, function (value, index) {
            //修改请求
            doRequest('FILE_OP', 'EDIT', value, index);
        });
    }
}

//删除弹出框
function deleteConfirm(opLocation, opName) {
    layer.confirm('确认要删除' + opName + '吗？', function (index) {
        doRequest(opLocation, "DELETE", "", index);
    });
}

//文件操作请求
function doRequest(opLocation, opType, opName, index) {
    //获取一级、二级目录名、文件名
    const primaryDirectory = document.getElementById("primaryDirectory").value;
    const secondaryDirectory = document.getElementById("secondaryDirectory").value;
    const fileName = document.getElementById("fileName").value;
    //管理请求
    $.ajax({
        url: '/note/manage',
        data: {
            "primaryDirectory": primaryDirectory,
            "secondaryDirectory": secondaryDirectory,
            "fileName": fileName,
            "opLocation": opLocation,
            "opName": opName,
            "opType": opType,
            "userNo": userNo
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
            } else {
                layer.msg('操作失败，失败原因：' + res.msg);
            }
        },
        //失败/超时
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (textStatus === 'timeout') {
                layer.error('网络异常');
                setTimeout(function () {
                    layui.alert('重新请求');
                }, 3000);
            }
            layer.error(errorThrown);
        }
    });
    //成功关闭弹窗
    layer.close(index);
}