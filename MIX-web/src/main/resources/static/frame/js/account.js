layui.define(['table', 'form'], function() {
    const $ = layui.$
        , form = layui.form
        , table = layui.table;

    //账号管理
    table.render({
        elem: '#LAY-account-manage'
        , url: '/note/account/page' + "?userNo=" + window.localStorage["loginNo"]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 100}
            , {field: 'address', title: '地址', Width: 50}
            , {field: 'account', title: '账号', Width: 50}
            , {field: 'password', title: '密码', Width:50}//toolbar: '#table-password-operate'
            , {field: 'accountType', title: '账号类型', Width: 50}
            , {field: 'remark', title: '备注', width: 100}
            , {field: 'updatedAt', title: '更新时间', width: 100, sort: true}
            , {title: '操作', width: 210, align: 'center', fixed: 'right', toolbar: '#table-account-operate'}
        ]]
        , page: true
        , limit: 10
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-account-manage)', function (obj) {
        const data = obj.data;
        //删除
        if (obj.event === 'del') {
            deleteAccount(data.id, obj);
        }

        //编辑 TODO
        if(obj.event === 'edit'){
            //弹出层，自定义弹出填写窗口
            layer.msg("工程师还在路上...");
        }

        //链接
        if(obj.event === 'link'){
            //打开新网页
            window.open(data.address);
        }
    });

    //监听搜索
    form.on('submit(LAY-account-search)', function(data){
        const field = data.field;
        //执行重载
        table.reload('LAY-account-manage', {
            where: field
        });
    });

    //操作按钮点击事件监听
    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
        const type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //事件
    const active = {
        batchdel: function () {
            batchDelete();
        }
        , add: function () {
            add();
        }
        ,download:function(){
            downloadTemplate();
        }
    };

    //下载模版
    function downloadTemplate(){
        //下载模版
        //获取XMLHttpRequest
        const xmlHttpRequest = new XMLHttpRequest();
        //发起请求
        xmlHttpRequest.open("POST", "/file/download?fileName=账号批量导入模版.xlsx", true);
        //设置请求头类型
        xmlHttpRequest.setRequestHeader("Content-type", "application/json");
        xmlHttpRequest.setRequestHeader("id", "1");
        xmlHttpRequest.responseType = "blob";
        //返回
        xmlHttpRequest.onload = function() {
            //alert(this.status);
            const content = xmlHttpRequest.response;
            // 组装a标签
            const elink = document.createElement("a");

            //拼接下载的文件名
            //设置文件下载路径
            elink.download = "账号批量导入模版.xlsx";
            elink.style.display = "none";
            const blob = new Blob([content]);

            //解决下载不存在文件的问题，根据blob大小判断
            if(blob.size===0){
                layer.msg('服务器没找到此文件，请联系管理员!');
            }else{
                elink.href = URL.createObjectURL(blob);
                document.body.appendChild(elink);
                elink.click();
                document.body.removeChild(elink);
            }
        };
        xmlHttpRequest.send();
    }

    //批量导入
    layui.use('upload', function () {
        const upload = layui.upload;

        //执行实例
        const uploadInst = upload.render({
            elem: '#import' //绑定元素
            , url: '/note/account/import' //上传接口
            , data: {"userNo": window.localStorage["loginNo"]}
            , exts: 'xls|xlsx'
            , enctype: 'multipart/form-data'
            , number: '1'
            , multiple: false
            , drag: true
            , size: 1024 * 15
            , done: function (res) {
                //上传完毕回调
                if (res.code === "0000") {
                    layer.msg("上传成功！请至“文件管理”查看具体的导入结果");
                } else {
                    layer.msg("上传失败！原因：" + res.msg);
                }
            }
            , error: function () {
                //请求异常回调
                layer.confirm("上传失败，您是否要重新上传？", {
                    btn: ['确定', '取消'],
                    icon: 3,
                    title: "提示"
                }, function () {
                    //关闭询问框
                    layer.closeAll();
                    //重新调用上传方法
                    uploadInst.upload();
                })
            }
        });
    });

    //账户新增
    function add(){
        layer.open({
            type: 2
            , title: '新增账号'
            , content: 'accountForm'
            , area: ['500px', '500px']
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                const data = $(layero).find('iframe')[0].contentWindow.callBackData();

                //ajax请求
                $.ajax({
                    url: '/note/account/add',
                    data: data,
                    dataType: 'json',//数据类型
                    type: 'GET',//类型
                    timeout: 3000,//超时
                    //请求成功
                    success: function (res) {
                        if (res.code === "0000") {
                            //成功
                            layer.msg("新增成功");
                            layer.close(index); //关闭弹层
                        } else {
                            layer.msg("新增失败：失败原因：" + res.msg);
                        }
                    },
                    //失败/超时
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (textStatus === 'timeout') {
                            layer.msg('网络异常');
                        }
                        layer.msg("失败原因：" + errorThrown);
                    }
                })
                //执行重载
                table.reload('LAY-account-manage');
            }
        });
    }

    //账户信息删除
    function deleteAccount(id, obj){

        layer.confirm('真的删除id为'+ id +'的账号信息么', function (index) {
            //删除文件数据
            $.ajax({
                url: '/note/account/delete',
                data: {"id": id, "userNo": window.localStorage["loginNo"]},
                dataType: 'json',//数据类型
                type: 'GET',//类型
                timeout: 3000,//超时
                //请求成功
                success: function (res) {
                    if (res.code !== "0000") {
                        layer.msg("删除失败，失败原因：" + res.msg);
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

            //表格删除
            obj.del();
            layer.close(index);
        });
    }

    //批量删除
    function batchDelete(){
        const checkStatus = table.checkStatus('LAY-account-manage')
            , checkData = checkStatus.data; //得到选中的数据

        if (checkData.length === 0) {
            return layer.msg('请选择数据');
        }

        layer.confirm('确定批量删除吗？', function (index) {
            //批量
            batchDeleteData(checkData);
            //关闭弹层
            layer.close(index);
            //执行重载
            table.reload('LAY-account-manage');
            layer.msg('已删除');
        });
    }

    //批量删除请求
    function batchDeleteData(checkData){
        for(let i = 0; i< checkData.length; i++){

            //删除文件数据
            $.ajax({
                url: '/note/account/delete',
                data: {"id": checkData[i].id, "userNo": window.localStorage["loginNo"]},
                dataType: 'json',//数据类型
                type: 'GET',//类型
                timeout: 3000,//超时
                //请求成功
                success: function (res) {
                    if (res.code !== "0000") {
                        layer.msg("删除失败，失败原因：" + res.msg);
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
        }
    }
})