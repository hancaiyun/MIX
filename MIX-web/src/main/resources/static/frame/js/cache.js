//查询
function queryCache() {
    layui.use('layer', function () {
        // 操作对象
        const layer = layui.layer;
        const $ = layui.jquery;
        //请求缓存查询
        const key = document.getElementById("key").value;
        if(key === "" || key == null){
            layer.msg("请填写KEY值", {icon: 2});
            setTimeout(function () {
            }, 1000);
            return;
        }
        $.ajax({
            url: '/cache/queryCache',
            data: {"key": key},
            dataType: 'json',//数据类型
            type: 'GET',//请求方式
            timeout: 3000,//超时时间
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    if(res.data.value == null){
                        //清空表单中的value跟expire
                        document.getElementsByName("value")[0].value = null;
                        document.getElementsByName("expire")[0].value = null;
                        layer.msg("无此KEY值缓存信息", {icon: 1});
                        setTimeout(function () {
                        }, 1000);
                    }else{
                        //回填value与expireTime
                        document.getElementsByName("value")[0].value = res.data.value;
                        document.getElementsByName("expire")[0].value = res.data.leftExpireTime;
                    }
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '查询失败，失败原因：' + res.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    alert('网络异常');
                    setTimeout(function () {
                    }, 30000);
                }
                alert(errorThrown);
            }
        });
    });
}

//新增
function addCache() {
    layui.use('layer', function () {
        // 操作对象
        const layer = layui.layer;
        const $ = layui.jquery;
        //请求缓存新增
        const key = document.getElementById("key").value;
        const value = document.getElementById("value").value;
        const expire = document.getElementById("expire").value;
        if(key === "" || key == null){
            layer.msg("请填写KEY值", {icon: 2});
            setTimeout(function () {
            }, 1000);
            return;
        }
        if(value === "" || value == null){
            layer.msg("请填写VALUE", {icon: 2});
            setTimeout(function () {
            }, 1000);
            return;
        }
        $.ajax({
            url: '/cache/addCache',
            data: {"key": key, "value": value, "expire": expire},
            dataType: 'json',//数据类型
            type: 'GET',//请求方式
            timeout: 3000,//超时时间
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    layer.msg("新增缓存成功", {icon: 1});
                    setTimeout(function () {
                    }, 1000);
                } else {
                    layer.open({
                        title: '提示信息'
                        , content: '新增缓存失败，失败原因：' + res.msg
                    });
                }
            },
            //失败/超时
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (textStatus === 'timeout') {
                    alert('网络异常');
                    setTimeout(function () {
                    }, 30000);
                }
                alert(errorThrown);
            }
        });
    });
}

//删除
function deleteCache() {
    layui.use('layer', function () {
        // 操作对象
        const layer = layui.layer;
        const $ = layui.jquery;

        //KEY值获取
        const key = document.getElementById("key").value;
        if(key === "" || key == null){
            layer.msg("请填写KEY", {icon: 2});
            setTimeout(function () {
            }, 1000);
            return;
        }
        //确认弹窗
        layer.open({
            title: '缓存清理确认提示信息'
            , btn: ['确定']
            , content: '确认清理KEY为'+ key +'的缓存？'
            ,yes:function (index, layero) {
                //关闭弹窗
                layer.close(index);
                //请求删除
                $.ajax({
                    url: '/cache/deleteCache',
                    data:{"key": key},
                    dataType: 'json',//数据类型
                    type: 'GET',//请求方式
                    timeout: 3000,//超时时间
                    //请求成功
                    success: function (res) {
                        if (res.code === "0000") {
                            //展示主页
                            layer.msg("缓存已清理", {icon: 1});
                            setTimeout(function () {
                            }, 1000);
                        } else {
                            layer.open({
                                title: '提示信息'
                                , content: '缓存清理失败，失败原因：' + res.msg
                            });
                        }
                    },
                    //失败/超时
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        if (textStatus === 'timeout') {
                            alert('网络异常');
                            setTimeout(function () {
                            }, 30000);
                        }
                        alert(errorThrown);
                    }
                });
            }
        });
    });
}