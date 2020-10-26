layui.config({
    base: '/frame/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'laypage','layer'], function(){
    const laypage = layui.laypage,
        layer = layui.layer,
        $ = layui.jquery;

    //初始化
    $(function () {
        initLayPage();
    });

    //分页
    function initLayPage(pageConf){
        if(!pageConf){
            pageConf = {};
            pageConf.pageSize = 5;
            pageConf.currentPage = 1;
        }
        //请求
        $.ajax({
            url: '/note/queryShare',
            data: {"userNo": window.localStorage["loginNo"], "current":pageConf.currentPage, "limit":pageConf.pageSize},
            dataType: 'json',
            type: 'GET',
            timeout: 3000,
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    //data
                    showNote(res.data.pageResult);
                    //page
                    showData(res.data, pageConf);
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '共享文档查询失败，失败原因：' + res.msg
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
    }

    //数据展示，分页插件设置
    function showData(data, pageConf) {
        //page
        laypage.render({
            elem: 'page',
            count: data.count,
            curr: pageConf.currentPage,
            limit: pageConf.pageSize,
            jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    //分页查询
                    pageConf.currentPage = obj.curr;
                    pageConf.pageSize = obj.limit;
                    initLayPage(pageConf);
                }
            }
        });
    }

    //共享文档列表展示
    function showNote(data) {
        let show = document.getElementById("shareDocumentList");
        let html = '';
        for (let i = data.length - 1; i >= 0; i--) {

            //跨越面传参组装
            const parameter = "loginNo=" + window.localStorage["loginNo"] +
                              "&id=" + data[i].id +
                              "&nickName=" + data[i].nickName +
                              "&headCopy=" + data[i].headCopy;

            html = "<div class=\"caller-item\">\n" +
                "<img src=/file/download?fileName=" + data[i].headCopy + " alt=\"\" class=\"caller-img caller-fl\">\n" +
                "<div class=\"caller-main caller-fl\">       \n" +
                "<p>" + data[i].nickName + " <em>" + data[i].updatedAt + "</em></p>\n" +
                "<p class=\"caller-adds\" lay-href=\"community/documentDetail?parameter=" + parameter + "\"><" +
                "em><strong><font size=\"4\">" + data[i].documentName + "</font></strong></em></p>\n" +
                "</div>\n" +
                "</div></<hr>" +
                html;
        }
        show.innerHTML = html;
    }
});