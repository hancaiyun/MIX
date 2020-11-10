layui.config({
    base: '/frame/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index','laypage','layer'], function(){
});

/**
 * 点赞
 */
function prize(){
    if(document.getElementById("prize").className === "layui-btn layui-btn-primary layui-btn-sm"){
        document.getElementById("prize").className = "layui-btn layui-btn-normal layui-btn-sm";
        document.getElementById("prize").innerHTML = "<i class=\"layui-icon layui-icon-praise\"></i>点赞16";
    }else{
        document.getElementById("prize").className = "layui-btn layui-btn-primary layui-btn-sm"
        document.getElementById("prize").innerHTML = "<i class=\"layui-icon layui-icon-praise\"></i>点赞15";
    }
}

/**
 * 显示评论区, 展示评论列表
 */
function comment(){
    document.getElementById("commentArea").style.display="block";
    initLayPage();
}

//分页
function initLayPage(pageConf){
    const $ = layui.jquery;
    const layer = layui.layer;

    if(!pageConf){
        pageConf = {};
        pageConf.pageSize = 5;
        pageConf.currentPage = 1;
    }
    const id = document.getElementById("documentId").innerText;
    //请求
    $.ajax({
        url: '/note/comment/pageQuery',
        data: {"subjectId": id, "current":pageConf.currentPage, "limit":pageConf.pageSize},
        dataType: 'json',
        type: 'GET',
        timeout: 3000,
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                //data
                showNote(res.data);
                //page
                showData(res, pageConf);
            } else {
                layer.open({
                    title: '提示信息'
                    , content: '评论列表查询失败，失败原因：' + res.msg
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

    const laypage = layui.laypage;
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

//评论列表展示
function showNote(data) {
    let show = document.getElementById("comment");
    let html = '';
    for (let i = data.length - 1; i >= 0; i--) {
        html = "<div class=\"caller-item\">\n" +
            "<img src=/file/download?fileName=" + data[i].headCopy + " alt=\"\" class=\"caller-img caller-fl\">\n" +
            "<div class=\"caller-main caller-fl\">       \n" +
            "<p><strong>" + data[i].nickName + "：<em>" + data[i].content + "</em></strong></p>\n" +
            "<p class=\"caller-adds\"><" +
            "em>" + data[i].updatedAt + "</em></p>\n" +
            "</div>" +
            "</div></<hr>" + html;
    }
    show.innerHTML = html;
}

/**
 * 评论提交
 */
function commitComment(){
    const $ = layui.jquery;
    const layer = layui.layer;
    const content = document.getElementById("content").value;
    const subjectId = document.getElementById("documentId").innerText;
    if(content == null || content === "" ){
        layer.alert("评论不允许为空");
        return;
    }

    //发表评论请求
    $.ajax({
        url: '/note/comment/commit',
        data: {"userNo": window.localStorage["loginNo"], "content": content, "subjectId":subjectId},
        dataType: 'json',
        type: 'GET',
        timeout: 3000,
        //请求成功
        success: function (res) {
            if (res.code === "0000") {
                //清空评论文本框
                document.getElementById("content").value = '';
                //刷新评论
                initLayPage();
                layer.msg("评论成功");
            } else {
                layer.open({
                    title: '提示信息'
                    , content: '评论失败，失败原因：' + res.msg
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

/**
 * 删除共享文件
 * @param id  文件id
 */
function cancelShare(){
    const $ = layui.jquery,
        layer = layui.layer;
    const id = document.getElementById("documentId").innerText;

    layer.confirm('确认删除共享吗， 删除后别人将无法查看此文档', function (index) {
        //删除文件数据
        $.ajax({
            url: '/note/unShare',
            data: {"id": id, "userNo": window.localStorage["loginNo"]},
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
                        , content: '删除失败，失败原因：' + res.msg
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

        //关闭弹窗
        layer.close(index);
    });
}