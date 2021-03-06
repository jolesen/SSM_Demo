/**
 * 初始化样本领用详情对话框
 */
var SampleUsedInfoDlg = {
    sampleUsedInfoData : {}
};
var SampleUsedDetail = {
        id:"SampleUsedDetailTable",
        table: null,
        layerIndex: -1
    }

SampleUsedDetail.initColumn = function () {
        return [
                {title: 'sampleUsedId', field: 'sample_used_id', visible: false, align: 'center', valign: 'middle'},
                {title: '样本id', field: 'sample_id', visible: false, align: 'center', valign: 'middle'},
                {title: '项目编号', field: 'detection_item', visible: true, align: 'center', valign: 'middle'},
                {title: '实验室编号', field: 'lab_code', visible: true, align: 'center', valign: 'middle'},
                {title: '样本编号', field: 'sample_number', visible: true, align: 'center', valign: 'middle'},
                {title: '受检人姓名', field: 'subject_name', visible: true, align: 'center', valign: 'middle'},
                {title: '领用人名字', field: 'user_name', visible: true, align: 'center', valign: 'middle'},
                {title: '领用日期', field: 'use_date', visible: true, align: 'center', valign: 'middle'},
                {title: '归还日期', field: 'return_date', visible: true, align: 'center', valign: 'middle'},
                {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}
                ];
    };

 

/**
 * 清除数据
 */
SampleUsedInfoDlg.clearData = function() {
    this.sampleUsedInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleUsedInfoDlg.set = function(key, val) {
    this.sampleUsedInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleUsedInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SampleUsedInfoDlg.close = function() {
    parent.layer.close(window.parent.SampleUsed.layerIndex);
}

/**
 * 收集数据
 */
SampleUsedInfoDlg.collectData = function() {
    this.set('sampleIds').set('useDate').set('returnDate').set('remarks').set('userName');
}

/**
 * 提交添加
 */
SampleUsedInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sampleUsed/add", function(data){
        Feng.success("领用成功!");
        window.parent.SampleUsed.table.refresh();
        SampleUsedInfoDlg.close();
    },function(data){
        Feng.error("领用失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleUsedInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SampleUsedInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sampleUsed/update", function(data){
        Feng.success("修改成功!");
        window.parent.SampleUsed.table.refresh();
        SampleUsedInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleUsedInfoData);
    ajax.start();
}

/*
 * 以下到下划线为提交Excel表格的js
 */

var uploader = WebUploader.create({

    // swf文件路径
    swf: Feng.ctxPath
	+ '/static/js/plugins/webuploader/Uploader.swf',
    // 文件接收服务端。
    server: Feng.ctxPath + '/sampleUsed/importExcel',
    fileVal: "uploadFile",
    accept : {
		title : 'Excel',
		extensions : 'xlsx',
        mimeTypes : 'application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	},
	formData: {  
/*		entityName: "com.stylefeng.guns.modular.business.entity.Detection",
		map:"检测结果,result,突变位点,mutationSite,检测时间,detectionDate,备注,remarks",
		requiredField:"result,mutationSite,detectionDate",
		specialfieldName:"detectorId",
		title:"Detection",*/
		importFieldName:"实验室编号,取样编号,检测结果,突变位点,检测时间,检测者,备注,风险",
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
    $( '#'+file.id ).find('p.state').text('已上传');
    window.parent.Sample.table.refresh();
});

uploader.on('uploadError', function( file ,reason ) {
    $( '#'+file.id ).find('p.state').text("导入失败："+errorMessage.error);
});
//导入完成后的检测
uploader.on( 'uploadAccept', function( file,ret ) {
      errorMessage=ret;
});
//完成后删进度条
uploader.on( 'uploadComplete', function( file ) {  
    $( '#'+file.id ).find('.progress').remove();  
});  

$(function () {
	var id = $("#sample_id").val();
    var defaultColunms = SampleUsedDetail.initColumn();
    var table = new BSTable(SampleUsedDetail.id, '/sampleUsed/listDetail/'+id,defaultColunms);
    table.setPaginationType("client");
    SampleUsedDetail.table = table.init();
});





