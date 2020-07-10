layui.define(['table', 'form'], function() {
    const $ = layui.$
        , table = layui.table;

    //文件管理
    table.render({
        elem: '#LAY-file-manage'
        , url: '/note/account/page' + "?userNo=" + window.localStorage["loginNo"]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 200}
            , {field: 'address', title: '地址', Width: 50}
            , {field: 'account', title: '账号', width: 100}
            , {field: 'password', title: '密码', width:100}
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
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                //口令
                if (value !== "19921577717") {
                    layer.alert("口令验证失败！");
                    return;
                }
                layer.close(index);
                layer.confirm('真的删除行么', function (index) {
                    //删除文件数据
                    $.ajax({
                        url: '/note/file/delete',
                        data: {"fileId": data.fileId, "userNo": window.localStorage["loginNo"]},
                        dataType: 'json',//数据类型
                        type: 'GET',//类型
                        timeout: 3000,//超时
                        //请求成功
                        success: function (res) {
                            if (res.code === "0000") {
                                //成功
                                layer.alert("删除成功");
                            } else {
                                layer.open({
                                    title: '提示信息'
                                    , content: '文件删除失败，失败原因：' + res.msg
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

                    //表格删除
                    obj.del();
                    layer.close(index);
                });
            });
        }

        //播放
        if(obj.event === 'play'){

            let html = '<div class="wrap">';
            html += '<video  height="100%" width="100%" controls autobuffer>';
            html += '<source src="/note/file/preview?filePath='+ data.filePath +'" type="video/mp4" />';
            html += '</video>';
            html += '</div>';
            //弹出层
            layer.open({
                type: 1,
                title: "视频播放",
                content: html
            });
        }
    });
})