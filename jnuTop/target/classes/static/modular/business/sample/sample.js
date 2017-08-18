/**
 * 样本管理初始化
 */
var Sample = {
    id: "SampleTable",	//表格id
    seItem: [],		//选中的条目 可能多选 所以将null改成了[]
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Sample.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'sample_id', visible: false, align: 'center', valign: 'middle'},
        {title: '检测项目', field: 'detection_item', visible: true, align: 'center', valign: 'middle'},
        {title: '实验室编码', field: 'lab_code', visible: true, align: 'center', valign: 'middle'},
        {title: '样本编号', field: 'sample_number', visible: true, align: 'center', valign: 'middle'},
        {title: '受检者姓名', field: 'subject_name', visible: true, align: 'center', valign: 'middle'},
        {title: '销售', field: 'salesman', visible: true, align: 'center', valign: 'middle'},
        {title: '收样日期', field: 'accept_date', visible: true, align: 'center', valign: 'middle'},
        {title: '样本类型', field: 'sample_type', visible: true, align: 'center', valign: 'middle'},
        {title: '运输条件', field: 'transport_condition', visible: true, align: 'center', valign: 'middle'},
        {title: '血液到样温度', field: 'blood_temperature', visible: true, align: 'center', valign: 'middle'},
        {title: '理论出报告时间', field: 'expected_report_time', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '样本来源', field: 'sample_source', visible: true, align: 'center', valign: 'middle'},
        {title: '是否已提取', field: 'extracted', visible: true, align: 'center', valign: 'middle'},
        {title: '样本储存位置', field: 'sample_storage', visible: true, align: 'center', valign: 'middle'},
        {title: '报告日期14自然日', field: 'detection_duration', visible: true, align: 'center', valign: 'middle'},
           
    ];
};

/**
 * 检查是否选中
 */
Sample.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	//将选中的值 循环录入数组Sample.seItem中
    	for( var i = 0; i < selected.length; i++) {
    		Sample.seItem[i] = selected[i];
    	}
        
        return true;
    }
};

/**
 * 点击上传附件
 */
Sample.uploadFile= function () {
    var index = layer.open({
        type: 2,
        title: '附件上传',
        area: ['500px', '250px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sample/sample_uploadFile'
    });
    console.log(Feng.ctxPath + "/sample/sample_uploadFile");
    this.layerIndex = index;
};


/**
 * 点击添加样本
 */
Sample.openAddSample = function () {
    var index = layer.open({
        type: 2,
        title: '添加样本',
        area: ['1100px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sample/sample_add'
    });
    console.log(Feng.ctxPath + "/sample/sample_add");
    this.layerIndex = index;
};

/**
 * 点击上传附件
 */
Sample.uploadFile= function () {
    var index = layer.open({
        type: 2,
        title: '附件上传',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sample/sample_uploadFile'
    });
    console.log(Feng.ctxPath + "/sample/sample_uploadFile");
    this.layerIndex = index;
};



/**
 * 点击修改按钮时
 */
Sample.openSampleDetail = function () {
	   if (this.check()) {
	        var index = layer.open({
	            type: 2,
	            title: '修改样本',
	            area: ['1100px', '600px'], //宽高
	            fix: false, //不固定
	            maxmin: true,
	            content: Feng.ctxPath + '/sample/sample_update/' + this.seItem[0].sample_id
	        });
	        this.layerIndex = index;
	    }
};


/**
 * 删除样本
 */
Sample.delete = function () {
    if (this.check()) {
   //    var ajax = new $ax(Feng.ctxPath + "/sample/delete", function (data) {
    	var ajax = new $ax(Feng.ctxPath + "/sample/delete", function (data) {	
        	
    		
            Feng.success("删除成功!");
            
            Sample.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        
        //创建一个数组,通过循环遍历,将所选对象的id传过去,格式是 ids=500,501,502
        var array = new Array();
    	for(var i = 0; i < this.seItem.length; i++){
			var id = this.seItem[i].sample_id
			array.push(id);
		}
		var ids = array.join(",");
        ajax.set("sampleId",ids);
        ajax.start();
    }
};

/**
 * 导出excel
 */
//Sample.outputExcel = function () {
//    if (this.check()) {
//        var ajax = new $ax(Feng.ctxPath + "/sample/outputExcel", function (data) {
//            Feng.success("导出成功!");
//        }, function (data) {
//            Feng.error("导出失败!" + "!");
//        });
//        
//        //创建一个数组,通过循环遍历,将所选对象的id传过去,格式是 ids=500,501,502
//        var array = new Array();
//    	for(var i = 0; i < this.seItem.length; i++){
//			var id = this.seItem[i].sample_id
//			array.push(id);
//		}
//		var ids = array.join(",");
//        ajax.set("sampleId",ids);
//        ajax.set("title","检测项目,实验室编码,样本编号,受检者姓名,销售,收样日期,样本类型,运输条件,血液到样温度,理论出报告时间,备注,样本来源,是否已提取,样本储存位置,报告日期14自然日")
//        ajax.start();
//    }
//};

outputExcel = function () {
    if (Sample.check()) {
 
        //创建一个数组,通过循环遍历,将所选对象的id传过去,格式是 ids=500,501,502
        var array = new Array();
    	for(var i = 0; i <Sample.seItem.length; i++){
			var id = Sample.seItem[i].sample_id
			array.push(id);
		}
		var ids = array.join(",");
		$.ajax({
			url : "/sample/outputExcel",
			type : 'POST',
			data : {
				"sampleId":ids,
				"title":"检测项目,实验室编码,样本编号,受检者姓名,销售,收样日期,样本类型,运输条件,血液到样温度,理论出报告时间,备注,样本来源,是否已提取,样本储存位置,报告日期14自然日"
			},
			dataType : "JSON",
			success : function(returndata) {
				var downloadURL= "/sample/ajaxDownload";
				var filedir="E:\\projectTest";
				var filename="test.xls";
				Feng.success("导出成功!");
				 $.download(downloadURL, 'post', filedir, filename); // 下载文件
			},
			error : function(returndata) {
			
				 Feng.error("导出失败!" + returndata+"!");
					location.href='E:\\projectTest\\test.xls'
			}
		});
    }
};

//文件下载
jQuery.download = function(url, method, filedir, filename){
 jQuery('<form action="'+url+'" method="'+(method||'post')+'">' +  // action请求路径及推送方法
             '<input type="text" name="filedir" value="'+filedir+'"/>' + // 文件路径
             '<input type="text" name="filename" value="'+filename+'"/>' + // 文件名称
         '</form>')
 .appendTo('body').submit().remove();
};

/**
 * 查询样本列表
 */
Sample.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Sample.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Sample.initColumn();
    var table = new BSTable(Sample.id, "/sample/list", defaultColunms);
    table.setPaginationType("client");
    Sample.table = table.init();
});
