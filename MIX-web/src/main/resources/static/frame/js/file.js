layui.define(['table', 'form'], function() {
    const $ = layui.$
        , table = layui.table;

    //文件管理
    table.render({
        elem: '#LAY-file-manage'
        , url: '/note/file/pageQuery' + "?userNo=" + window.localStorage["loginNo"]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'fileId', title: '文件ID', width: 200}
            , {field: 'fileName', title: '文件名', Width: 50}
            , {field: 'fileType', title: '文件类型', width: 100}
            , {field: 'createdAt', title: '上传时间', width:100, sort: true}
            , {title: '操作', width: 210, align: 'center', fixed: 'right', toolbar: '#table-file-operate'}
        ]]
        , page: true
        , limit: 10
        , height: 350
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-file-manage)', function (obj) {
        const data = obj.data;
        //删除
        if (obj.event === 'del') {
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
                            layer.msg("删除成功");
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
        }
        //预览
        if (obj.event === 'preview') {
            layer.photos({
                photos: {
                    "title": "查看图片"
                    ,"data": [{
                        "src": "/note/file/preview?filePath=" + data.filePath
                    }]
                }
                ,shade: 0.01
                ,closeBtn: 1
                ,anim: 5
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
        //下载
        if (obj.event === 'download') {

            //获取XMLHttpRequest
            const xmlHttpRequest = new XMLHttpRequest();
            //发起请求
            xmlHttpRequest.open("POST", "/note/file/download" + "?fileId=" + data.fileId, true);
            //设置请求头类型
            xmlHttpRequest.setRequestHeader("Content-type", "application/json");
            xmlHttpRequest.setRequestHeader("id",data.id);
            xmlHttpRequest.responseType = "blob";
            //返回
            xmlHttpRequest.onload = function() {
                //alert(this.status);
                const content = xmlHttpRequest.response;
                // 组装a标签
                const elink = document.createElement("a");

                //拼接下载的文件名
                //设置文件下载路径
                elink.download = data.fileName;
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
    });
})