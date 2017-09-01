/**
 * web-upload 工具类
 * 
 * 需要传入的对象应该是Json对象，例子格式为：
 * {
 * server:  Feng.ctxPath + '/sample/importExcel'
 * accept : {
					title : '上传Excel',
					extensions : 'xlsx',
			        mimeTypes : 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
				},
 * formData: {  
					entityName: "com.stylefeng.guns.modular.business.entity.Sample",
					map:"检测项目,detectionItem,样本编号,sampleNumber,报告所需时间,detectionDuration,实验室编码,labCode,受检者姓名,subjectName,销售,salesman,收样日期,acceptDate,样本类型,sampleType,运输条件,transportCondition,血液到样温度,bloodTemperature,理论出报告时间,expectedReportTime,备注,remark,样本来源,sampleSource,是否需要提取,extracted,样本储存位置,sampleStorage",
					requiredField:"detectionItem,sampleNumber,detectionDuration",
					specialfieldName:"acceptDate,expectedReportTime,labCode",
					title:"Sample",
			    }
 *}			    			
 * @author djb
 */
(function() {
	var allParameter;
	var $WebUploadFile = function(parameter) {
		allParameter=parameter;
	};

	$WebUploadFile.prototype = {
		/**
		 * 初始化webUploader
		 */
		init : function() {
			var uploader = this.create();
			this.bindEvent(uploader);
			return uploader;
		},
		
		/**
		 * 创建webuploader对象
		 */
		create : function() {
			var webUploader = WebUploader.create({
				// swf文件路径
			    swf: Feng.ctxPath
				+ '/static/css/plugins/webuploader/Uploader.swf',
			    // 文件接收服务端。
			    server:   allParameter.server,
			    fileVal: "uploadFile",
			    threads: 1,
			    
				pick : {
					id : '#FilePicker',
				},
				accept : {
					title : allParameter.accept.title,
					extensions : allParameter.accept.extensions,
			        mimeTypes : allParameter.accept.mimeTypes,
				},
				
				formData: allParameter.formData,  

			});
			
			return webUploader;
		},

		/**
		 * 绑定事件
		 */
		bindEvent : function(bindedObj) {

			$("#FileBtn").on('click', function(file) {
				bindedObj.upload();  
			});
			

			//当文件进入队列时
			bindedObj.on( 'fileQueued', function( file ) {
			    $("#Filelist").append( '<div id="' + file.id + '" class="item">' +
			        '<h4 class="info">' + file.name + '</h4>' +
			        '<p class="state">等待上传...</p>' +
			    '</div>' );
			    $("#FileBtn").css({'background-color': '#1ab394'});
			});

			//当文件上传流程被触发时
			bindedObj.on( 'startUpload', function( file ) {
			    $("#FileBtn").css({'background-color': '#c2c2c2'});
			});

			bindedObj.on( 'uploadProgress', function( file, percentage ) {
			    var $li = $( '#'+file.id ),
			        $percent = $li.find('.progress .progress-bar');

			    // 避免重复创建
			    if ( !$percent.length ) {
			        $percent = $('<div class="progress progress-striped active">' +
			          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
			          '</div>' +
			        '</div>').appendTo( $li ).find('.progress-bar');
			    }

			    $li.find('p.state').text('上传中');

			    $percent.css( 'width', percentage * 100 + '%' );
			});

			bindedObj.on( 'uploadSuccess', function( file ) {
			    $( '#'+file.id ).find('p.state').text('已上传');
			    window.parent.Sample.table.refresh();
			});

			bindedObj.on('uploadError', function( file ,reason ) {
			    $( '#'+file.id ).find('p.state').text("导入失败："+errorMessage.error);
			});
			//导入完成后的检测
			bindedObj.on( 'uploadAccept', function( file,ret ) {
			      errorMessage=ret;
			});
			//完成后删进度条
			bindedObj.on( 'uploadComplete', function( file ) {  
			    $( '#'+file.id ).find('.progress').remove();  
			});  
		},

       
	};

	window.$WebUploadFile = $WebUploadFile;

}());