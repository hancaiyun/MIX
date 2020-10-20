layui.define(['form', 'upload'], function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  ,form = layui.form
  ,upload = layui.upload;

  var $body = $('body');
  
  //自定义验证
  form.verify({
    nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return '用户名不能有特殊字符';
      }
      if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return '用户名首尾不能出现下划线\'_\'';
      }
      if(/^\d+\d+\d$/.test(value)){
        return '用户名不能全为数字';
      }
    }
    
    //我们既支持上述函数式的方式，也支持下述数组的形式
    //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
    ,pass: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ]
    
    //确认密码
    ,repass: function(value){
      if(value !== $('#LAY_password').val()){
        return '两次密码输入不一致';
      }
    }
  });
  
  //网站设置
  form.on('submit(set_website)', function(obj){
    layer.msg(JSON.stringify(obj.field));
    
    //提交修改
    /*
    admin.req({
      url: ''
      ,data: obj.field
      ,success: function(){
        
      }
    });
    */
    return false;
  });
  
  //邮件服务
  form.on('submit(set_system_email)', function(obj){
    layer.msg(JSON.stringify(obj.field));
    
    //提交修改
    /*
    admin.req({
      url: ''
      ,data: obj.field
      ,success: function(){
        
      }
    });
    */
    return false;
  });
  
  //设置我的资料
  form.on('submit(setMyInfo)', function(obj){
    const data = obj.field;
    data.userNo = window.localStorage["loginNo"];
    //提交修改
    admin.req({
      url: '/set/user/update'
      ,data: obj.field
      ,success: function(res){
        if(res.code === "0000"){
          //成功提示
          layer.msg("用户信息更新成功");
        }else{
          layer.msg("用户信息更新失败，失败原因："+ res.msg);
        }
      }
    });
    return false;
  });


  //上传头像
  var avatarSrc = $('#LAY_avatarSrc');
  upload.render({
    url: '/file/upload/'
    ,elem: '#LAY_avatarUpload'
    ,done: function(res){
      if(res.code === "0000"){
        layer.msg("图片上传成功", {icon: 1});
        avatarSrc.val(res.data);
        $("#LAY_avatarSrc").attr("value", res.data);
      } else {
        layer.msg(res.msg, {icon: 5});
      }
    }
  });
  
  //查看头像
  admin.events.avartatPreview = function(othis){
    var src = avatarSrc.val();
    layer.photos({
      photos: {
        "title": "查看头像" //相册标题
        ,"data": [{
          "src": "/file/download?fileName=" + src //原图地址
        }]
      }
      ,shade: 0.01
      ,closeBtn: 1
      ,anim: 5
    });
  };

  //设置密码
  form.on('submit(setmypass)', function(obj){

    obj.field.userNo = window.localStorage["loginNo"];
    //提交修改
    admin.req({
      url: '/set/user/password'
      ,data: obj.field
      ,success: function(res){
        if(res.code === "0000"){
          layer.msg("密码修改成功，即将重新登录", {icon: 1});
          setTimeout(function () {
            top.location = "/logout";
          }, 3000);
        } else {
          layer.msg(res.msg, {icon: 5});
          return true;
        }
      }
    });
  });
  
  //对外暴露的接口
  exports('set', {});
});