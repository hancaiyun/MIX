layui.use('element',function () {
    const $ = layui.jquery;
    const element = layui.element;
    //定时获取数据并刷新element（3秒）
    setInterval('reloadView()',3000);
    window.reloadView=function(){
        $.ajax({
            url: '/home/console/systemInfo',
            dataType: 'json',//数据类型
            type: 'GET',//类型
            timeout: 3000,//超时时间
            //请求成功
            success: function (res) {
                if (res.code === "0000") {
                    showPercent(res.data);
                } else {
                    layer.msg('系统信息查询失败，失败原因：' + res.msg);
                }
            }
        });

    };
    //资源百分比设置
    function showPercent(data){
        //CPU
        $('#cpu').attr('lay-percent',data.CPU+'%');
        if(data.CPU > 90){
            $("#cpu").addClass("layui-bg-red");
        }else{
            const cpu = document.getElementById("cpu");
            removeClass(cpu, 'layui-bg-red');
        }
        //memory
        $('#memory').attr('lay-percent',data.MEMORY +'%');
        if(data.MEMORY > 85){
            $("#memory").addClass("layui-bg-red");
        }else{
            const memory = document.getElementById("memory");
            removeClass(memory, 'layui-bg-red');
        }
        //disk
        $('#disk').attr('lay-percent',data.DISK +'%');
        if(data.DISK > 90){
            $("#disk").addClass("layui-bg-red");
        }else{
            const disk = document.getElementById("disk");
            removeClass(disk, 'layui-bg-red');
        }
        element.init();
        element.progress('cpu', data.CPU+'%');
        element.progress('memory', data.MEMORY+'%');
        element.progress('disk', data.DISK+'%');
    }

    //移除class
    function removeClass( elements,cName ){
        if(hasClass( elements,cName )){
            elements.className = elements.className.replace( new RegExp( "(\\s|^)" + cName + "(\\s|$)" )," " );
        }
    }

    //判断是否有class
    function hasClass( elements,cName ){
        return !!elements.className.match( new RegExp( "(\\s|^)" + cName + "(\\s|$)") );
    }

});

layui.use('carousel', function(){
    const carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#advertisement'
        ,width: '100%'   //设置容器宽度
        ,height: '185px' //设置容器高度
        ,arrow: 'always' //始终显示箭头
    });
});