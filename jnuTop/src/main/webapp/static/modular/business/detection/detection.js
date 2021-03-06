/**
 * 检测结果管理初始化
 */
var Detection = {
    id: "DetectionTable",	//表格id
    seItem: [],		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Detection.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: 'id', field: 'detection_id', visible: false, align: 'center', valign: 'middle'},
        {title: '样本id', field: 'sample_id', visible: true, align: 'center', valign: 'middle'},
        {title: '检测结果', field: 'result', visible: true, align: 'center', valign: 'middle'},
        {title: '突变点', field: 'mutation_site', visible: true, align: 'center', valign: 'middle'},
        {title: '风险', field: 'risk', visible: true, align: 'center', valign: 'middle'},
        {title: '检测日期', field: 'detection_date', visible: true, align: 'center', valign: 'middle'},
        {title: '检测者', field: 'detector_name', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Detection.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	for (var i = 0; i < selected.length; i++) {
        	Detection.seItem[i] = selected[i];
        }

        return true;
    }
};

/**
 * 点击上传附件
 */
Detection.uploadFile = function () {
    var index = layer.open({
        type: 2,
        title: '附件上传',
        area: ['500px', '250px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/detection/detection_uploadFile'
    });
    console.log(Feng.ctxPath + "/detection/detection_uploadFile");
    this.layerIndex = index;
};

/**
 * 点击添加检测结果
 */
Detection.openAddDetection = function () {
    var index = layer.open({
        type: 2,
        title: '添加检测结果',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/detection/detection_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮
 */
Detection.openDetectionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '检测结果详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/detection/detection_update/' + Detection.seItem[0].detection_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除检测结果
 */
Detection.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/detection/delete", function (data) {
            Feng.success("删除成功!");
            Detection.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
      //创建一个数组,通过循环遍历,将所选对象的id传过去,格式是 ids=[500,501,502]
        var array = new Array();
        for (var i = 0; i < this.seItem.length; i++) {
            var id = this.seItem[i].detection_id;
            array.push(id);
        }
        var ids = array.toLocaleString();
        ajax.set("detectionId",ids);
        ajax.start();
    }
};




/**
 * 查询检测结果列表
 */
Detection.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Detection.table.refresh({query: queryData});
};


/**
 * 文件导出
 */
Detection.exportExcel = function () {

    if (this.check()) {
    	
	     var array = new Array();
	     for (var i = 0; i < this.seItem.length; i++) {
	         var id = this.seItem[i].detection_id;
	         array.push(id);
	     }
	     var ids = array.toLocaleString();
    	


        try {
            var elemIF = document.createElement("iframe");
            elemIF.src = "/detection/exportExcel?ids=" + ids;
            elemIF.style.display = "none";
            document.body.appendChild(elemIF);
        } catch (e) {

        }
    }

}




$(function () {
    var defaultColunms = Detection.initColumn();
    var table = new BSTable(Detection.id, "/detection/list", defaultColunms);
    table.setPaginationType("client");
    Detection.table = table.init();
});
