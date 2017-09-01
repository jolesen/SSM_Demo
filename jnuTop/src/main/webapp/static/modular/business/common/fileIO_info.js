/**
 * 文件上传下载的js
 * @author djb
 */

var errorMessage;//用来获取导入文件后的返回结果
var UPLOAD_EXCEL = "uploadEXCEL"
var UPLOAD_Attachment = "uploadAttachment";


/**
 * 点击导入Excel的按钮
 */
var uploadExcel= function (parameter) {
    var index = layer.open({
        type: 2,
        title: '附件上传',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fileIO/uploadExcel'+'?'+'entityName='+parameter.entityName+
        '&&map='+parameter.map+'&&requiredField='+parameter.requiredField+'&&specialfieldName='+parameter.specialfieldName
        +'&&title='+parameter.title,

    });
    console.log(Feng.ctxPath + "/fileIO/uploadExcel/"+JSON.stringify(parameter));
    this.layerIndex = index;
};

/**
 * 点击上传附件的按钮
 */
uploadAttachment = function (id,entityName) {
    var index = layer.open({
        type: 2,
        title: '附件上传',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fileIO/uploadAttachment/'+id+'/'+entityName
    });
    console.log(Feng.ctxPath + "/fileIO/uploadAttachment/"+id+'/'+entityName);
    this.layerIndex = index;
    
    
};

var outputExcel = function (parameter) {

        $.ajax({
            url: "/fileIO/outputExcel",
            type: 'POST',
            data: parameter.data,
            dataType: "JSON",
            success: function (returndata) {
                var downloadURL = "/fileIO/ajaxDownload";
                var filedir = returndata.filedir;
                var filename = returndata.filename;
                Feng.success("文件已导出到服务器!");
                $.download(downloadURL, 'post', filedir, filename); // 下载文件
            },
            error: function (returndata) {
                Feng.error("文件导出到服务器失败!" + returndata.responseJSON.error + "!");
            }
        });
    }

/**
 * 点击下载附件
 */
var downloadAttachment = function(parameter) {
	$.ajax({
		url : "/fileIO/downloadAttachment",
		type : 'POST',
		data :parameter.data,
		dataType : "JSON",
		success : function(returndata) {
			var downloadURL = "/fileIO/ajaxDownload";
			var filedir = returndata.filedir;
			var filename = returndata.filename;
			Feng.success("文件已导出到服务器!");
			$.download(downloadURL, 'post', filedir, filename); // 下载文件
		},
		error : function(returndata) {
			Feng.error(returndata.responseJSON.error + "!");
		}
	});
};


//文件下载
jQuery.download = function (url, method, filedir, filename) {
	jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' +  // action请求路径及推送方法
			'<input type="text" name="filedir" value="' + filedir + '"/>' + // 文件路径
			'<input type="text" name="filename" value="' + filename + '"/>' + // 文件名称
	'</form>')
	.appendTo('body').submit().remove();
};

var initWebUpload=function (inParameter) {
	var parameter;
	if($("#order").val()==UPLOAD_EXCEL){
	parameter=
		{
			 server:  Feng.ctxPath + '/fileIO/importExcel',
			 accept : {
								title : '上传Excel',
								extensions : 'xlsx',
						        mimeTypes : 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
							},
			 formData: {  
								entityName: $("#entityName").val(),
								map: $("#map").val(),
								requiredField:$("#requiredField").val(),
								specialfieldName:$("#specialfieldName").val(),
								title:$("#title").val(),
						    }
		}
	}else if($("#order").val()==UPLOAD_Attachment){

		parameter=
		{
			 server: Feng.ctxPath + '/fileIO/importAttachment',
			 accept : {
					title : '上传附件',
				},
				formData: {  
					theId: $("#theId").val(),
					entityName: "com.stylefeng.guns.modular.business.entity.Sample"
			    },  
		}
	}
    // 注册文件上传
    var FileUpload = new $WebUploadFile(parameter);
    FileUpload.init();

};

