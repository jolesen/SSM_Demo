/**
 * 检测结果管理初始化
 */
var Detection = {
    id: "DetectionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Detection.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '样本id', field: 'sample_id', visible: true, align: 'center', valign: 'middle'},
        {title: '检测结果', field: 'result', visible: true, align: 'center', valign: 'middle'},
        {title: '检测者id', field: 'detector_id', visible: true, align: 'center', valign: 'middle'},
        {title: '突变点', field: 'mutation_site', visible: true, align: 'center', valign: 'middle'},
        {title: '风险', field: 'risk', visible: true, align: 'center', valign: 'middle'},
        {title: '检测日期', field: 'detection_date', visible: true, align: 'center', valign: 'middle'},
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
        Detection.seItem = selected[0];
        return true;
    }
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
 * 打开查看检测结果详情
 */
Detection.openDetectionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '检测结果详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/detection/detection_update/' + Detection.seItem.id
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
        ajax.set("detectionId",this.seItem.id);
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

$(function () {
    var defaultColunms = Detection.initColumn();
    var table = new BSTable(Detection.id, "/detection/list", defaultColunms);
    table.setPaginationType("client");
    Detection.table = table.init();
});
