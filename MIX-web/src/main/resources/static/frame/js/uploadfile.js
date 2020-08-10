$(function() {
	layui.use([ 'layer', 'element', 'form', 'upload' ], function() {
		const layer = layui.layer,
			element = layui.element,
			form = layui.form,
			upload = layui.upload;
		form.render();

		//创建监听函数
		const xhrOnProgress = function (fun) {
			xhrOnProgress.onprogress = fun; //绑定监听
			//使用闭包实现监听绑
			return function () {
				//通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
				const xhr = $.ajaxSettings.xhr();
				//判断监听函数是否为函数
				if (typeof xhrOnProgress.onprogress !== 'function')
					return xhr;
				//如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
				if (xhrOnProgress.onprogress && xhr.upload) {
					xhr.upload.onprogress = xhrOnProgress.onprogress;
				}
				return xhr;
			}
		};

		const uploadFile = upload.render({
			elem: '#uploadFile', //绑定元素
			url: '/note/file/upload', //上传接口
			data:{"userNo": window.localStorage["loginNo"]},
			exts: 'jpg|png|jpeg|txt|doc|docx|ppt|pptx|xls|xlsx|mp4|flv|mod|mov|mkv|dv|mpeg|wmv|avi|zip|7z|rar|tgz',//限定上传类型
			//accept: file,//指定允许上传时校验的文件类型 images（图片）、file（所有文件）、video（视频）、audio（音频）
			//acceptMime: 'image/jpg, image/png, image/jpeg',//只筛选上述类型图片(选择上传文件时文件类型自动筛选)
			enctype: 'multipart/form-data',
			//number： '0',//0为不限制上传数量
			multiple: true,// 开启多文件上传
			drag:true, //是否允许拖拽上传
			size: 1024 * 150,//为0为不限制大小  150M
			xhr: xhrOnProgress,
			//监听xhr进度，并返回给进度条
			progress: function (value, element) { //上传进度回调 value进度值
				element.progress('upload_progress', value + '%'); //设置页面进度条
			},
			before: function () {
				//开始上传时候让进度条去除隐藏状态
				$("#upload_progress").removeClass("layui-hide");
			},
			done: function (res, index, upload) {
				//上传完毕回调
				layer.close(layer.index);
				if (res.code === "0000") {//此处只用于单文件图片的上传，若为多图片多文件请做其他处理
					element.progress('upload_progress', '100%');
					//填充文件名
					document.getElementsByName("fileName")[0].value = res.data.result.fileName;
					//填充文件类型
					document.getElementsByName("fileType")[0].value = res.data.result.fileType;
					layer.msg("上传成功！");
				}else{
					layer.msg("上传失败！原因：" + res.msg);
				}
			},
			error: function (index, upload) {
				//请求异常回调
				layer.close(layer.index);
				layer.confirm("上传失败，您是否要重新上传？", {
					btn: ['确定', '取消'],
					icon: 3,
					title: "提示"
				}, function () {
					//关闭询问框
					layer.closeAll();
					//重新调用上传方法
					uploadFile.upload();
				})
			}
		});
	})
});