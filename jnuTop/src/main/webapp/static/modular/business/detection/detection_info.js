/**
 * 初始化检测结果详情对话框
 */
var DetectionInfoDlg = {
    detectionInfoData : {}
};

/**
 * 清除数据
 */
DetectionInfoDlg.clearData = function() {
    this.detectionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DetectionInfoDlg.set = function(key, val) {
    this.detectionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DetectionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DetectionInfoDlg.close = function() {
    parent.layer.close(window.parent.Detection.layerIndex);
}

/**
 * 收集数据
 */
                                          
DetectionInfoDlg.collectData = function() {
	this.set('sampleId').set('result').set('mutationSite').set('risk').set('detectionDate')
    .set('remarks').set('detectorName').set('detectionId');
}

/**
 * 提交添加
 */
DetectionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/detection/add", function(data){
        Feng.success("添加成功!");
        window.parent.Detection.table.refresh();
        DetectionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    console.log(this.detectionInfoData);
    ajax.set(this.detectionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DetectionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/detection/update", function(data){
        Feng.success("修改成功!");
        window.parent.Detection.table.refresh();
        DetectionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.detectionInfoData);
    ajax.start();
}

/*
 * 以下到下划线为提交Excel表格的js
 */

var uploader = WebUploader.create({

    // swf文件路径
    swf: Feng.ctxPath
	+ '/static/css/plugins/webuploader/Uploader.swf',
    // 文件接收服务端。
    server: Feng.ctxPath + '/detection/importExcel',
    fileVal: "uploadFile",
    accept : {
		title : 'Excel',
		extensions : 'xlsx',
        mimeTypes : 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	},
	formData: {  
		importFieldName:"取样编号,实验室编号,检测结果,突变位点,检测时间,检测者,备注,风险",
		importFieldMethod:"setSampleNumber,setLabCode,setResult,setMutationSite,setDetectionDate,setDetectorName,setRemarks,setRisk",
		
    },  
    pick: '#picker',
//    auto: true, //选择文件后自动上传
    resize: false,
    
    duplicate:true//是否可以重复上传
});

$("#ctlBtn").on( 'click', function() {
    uploader.upload();  
  });  

//当文件进入队列时
uploader.on( 'fileQueued', function( file ) {
    $("#thelist").append( '<div id="' + file.id + '" class="item">' +
        '<h4 class="info">' + file.name + '</h4>' +
        '<p class="state">等待上传...</p>' +
    '</div>' );
    $("#ctlBtn").css({'background-color': '#1ab394'});
});

//当文件上传流程被触发时
uploader.on( 'startUpload', function( file ) {
    $("#ctlBtn").css({'background-color': '#c2c2c2'});
});

uploader.on( 'uploadProgress', function( file, percentage ) {
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

uploader.on( 'uploadSuccess', function( file ) {
    //$( '#'+file.id ).find('p.state').text('已上传');
	//$( '#'+file.id ).find('p.state').text(Message.errorInfo);
	if(Message.code == 200){
		$( '#'+file.id ).find('p.state').text('已上传');
	}else{
		$( '#'+file.id ).find('p.state').text("导入失败:"+Message.message);
	}
    window.parent.Detection.table.refresh();
});
//errorInfo
//errorMessage.error
uploader.on('uploadError', function( file ,reason ) {
    $( '#'+file.id ).find('p.state').text("导入失败："+errorMessage.errorInfo);
});
//导入完成后的检测
uploader.on( 'uploadAccept', function( file,ret ) {
      Message=ret;
});
//完成后删进度条
uploader.on( 'uploadComplete', function( file ) {  
    $( '#'+file.id ).find('.progress').remove();  
});  

$(function() {

});
