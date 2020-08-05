layui.define(['table', 'form'], function() {
    const table = layui.table;

    //文件管理
    table.render({
        elem: '#LAY-download-center-manage'
        , url: '/downloadCenter/pageQuery' + "?userNo=" + window.localStorage["loginNo"]
        , cols: [[
            {field: 'fileId', title: '文件ID', width: 200}
            , {field: 'fileDesc', title: '文件名', Width: 50}
            , {field: 'createResult', title: '状态', width: 100}
            , {field: 'createdAt', title: '生成时间', width:100, sort: true}
            , {title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-download-operate'}
        ]]
        , page: true
        , limit: 10
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-download-center-manage)', function (obj) {
        const data = obj.data;

        //下载
        if (obj.event === 'download') {

            //获取XMLHttpRequest
            const xmlHttpRequest = new XMLHttpRequest();
            //发起请求
            xmlHttpRequest.open("POST", "/downloadCenter/download" + "?fileId=" + data.fileId, true);
            //设置请求头类型
            xmlHttpRequest.setRequestHeader("Content-type", "application/json");
            xmlHttpRequest.setRequestHeader("id",data.id);
            xmlHttpRequest.responseType = "blob";
            //返回
            xmlHttpRequest.onload = function() {
                //alert(this.status);
                const content = xmlHttpRequest.response;
                // 组装a标签
                const alink = document.createElement("a");

                //拼接下载的文件名
                //设置文件下载路径
                alink.download = data.fileName;
                alink.style.display = "none";
                const blob = new Blob([content]);

                //解决下载不存在文件的问题，根据blob大小判断
                if(blob.size===0){
                    layer.msg('服务器没找到此文件，请联系管理员!');
                }else{
                    alink.href = URL.createObjectURL(blob);
                    document.body.appendChild(alink);
                    alink.click();
                    document.body.removeChild(alink);
                }
            };
            xmlHttpRequest.send();
        }
    });
})