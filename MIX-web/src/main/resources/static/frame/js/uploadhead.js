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
				var xhr = $.ajaxSettings.xhr();
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
			elem: '#upload', //绑定元素
			url: '/file/upload', //上传接口
			exts: 'jpg|png|jpeg',//限定上传类型
			//accept: images,//指定允许上传时校验的文件类型 images（图片）、file（所有文件）、video（视频）、audio（音频）
			acceptMime: 'image/jpg, image/png, image/jpeg',//只筛选上述类型图片
			data: {"userNo": "19921577717", "fileType": "PICTURE", "subFileType": "HEAD"}, //可选项 额外的参数，如：{id: 123, abc: 'xxx'}
			enctype: 'multipart/form-data',
			//number： '0',//0为不限制上传数量
			//multiple: true,// 开启多文件上传
			//drag:true, //是否允许拖拽上传
			size: 1024 * 3,//为0为不限制大小
			xhr: xhrOnProgress,
			//监听xhr进度，并返回给进度条
			progress: function (value) { //上传进度回调 value进度值
				element.progress('upload_progress', value + '%'); //设置页面进度条
			},
			before: function (obj) {
				//开始上传时候让进度条去除隐藏状态
				$("#upload_progress").removeClass("layui-hide");
				//或者设置loading
				//layer.load(1); //去除方法为 layer.close('loading'); 或者 layer.closeAll('loading');
			},
			//auto: false, //选择文件后不自动上传 默认值为true
			//bindAction: '#testListAction', //指向一个按钮触发上传
			//选择文件的回调 在文件被选择后触发，该回调会在 before 回调之前。一般用于非自动上传（即 auto: false ）的场景 预览图片等其他操作
			done: function (res, index, upload) {//在多文件上传中 每次成功将被调用一次
				//上传完毕回调
				layer.close(layer.index);
				if (res.code === "0000") {//此处只用于单文件图片的上传，若为多图片多文件请做其他处理
					element.progress('upload_progress', '100%');
					//填充文件名
					document.getElementsByName("head")[0].value = res.data.result.fileName;
					layer.msg("上传成功！");
				}
				//获取当前触发上传的元素
				//var item = this.item;
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