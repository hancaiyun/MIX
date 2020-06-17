layui.define(['table', 'form'], function(exports) {
    const $ = layui.$
        , table = layui.table
        , form = layui.form;

    //文件管理
    table.render({
        elem: '#LAY-file-manage'
        , url: '/note/file/pageQuery' + "?userNo=" + window.localStorage["loginNo"]
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'fileId', title: '文件ID', width: 100}
            , {field: 'fileName', title: '文件名', Width: 100}
            , {field: 'fileType', title: '文件类型', width: 100}
            , {field: 'createdAt', title: '上传时间', width:100, sort: true}
            , {title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-file-operate'}
        ]]
        , page: true
        , limit: 10
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-file-manage)', function (obj) {
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
                    obj.del();
                    layer.close(index);
                });
            });
        }
        //预览
        if (obj.event === 'preview') {
            //layer.alert("功能待开发");
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