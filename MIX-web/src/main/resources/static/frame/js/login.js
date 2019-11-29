layui.config({
    base: '../../frame/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'user'], function () {
    var $ = layui.$;
    var form = layui.form;
    form.render();
});
