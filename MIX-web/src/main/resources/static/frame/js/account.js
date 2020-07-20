layui.define(['table', 'form'], function() {
    const $ = layui.$
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
            , {field: 'password', title: '密码', Width:50, toolbar: '#table-password-operate'}
            , {field: 'remark', title: '备注', width: 100}
            , {field: 'updatedAt', title: '更新时间', width: 100, sort: true}
            , {title: '操作', width: 210, align: 'center', fixed: 'right', toolbar: '#table-account-operate'}
        ]]
        , page: true
        , limit: 10
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条 TODO
    table.on('tool(LAY-account-manage)', function (obj) {
        const data = obj.data;
        //删除
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                //删除文件数据
                $.ajax({
                    url: '/note/account/delete',
                    data: {"fileId": data.id, "userNo": window.localStorage["loginNo"]},
                    dataType: 'json',//数据类型
                    type: 'GET',//类型
                    timeout: 3000,//超时
                    //请求成功
                    success: function (res) {
                        if (res.code === "0000") {
                            //成功
                            layer.msg("删除成功");
                        } else {
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

        //编辑 TODO
        if(obj.event === 'edit'){

            //弹出层，自定义弹出填写窗口

        }

        //链接
        if(obj.event === 'link'){
            //打开新网页
            window.open(data.address);
        }
    });
})