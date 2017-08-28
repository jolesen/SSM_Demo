/**
 * 样本领用管理初始化
 */
var SampleUsed = {
    id: "SampleUsedTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SampleUsed.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: 'sampleId', field: 'sample_id', visible: false, align: 'center', valign: 'middle'},
        {title: '项目编号', field: 'detection_item', visible: true, align: 'center', valign: 'middle'},
        {title: '实验室编号', field: 'lab_code', visible: true, align: 'center', valign: 'middle'},
        {title: '样本编号', field: 'sample_number', visible: true, align: 'center', valign: 'middle'},
        {title: '受检人姓名', field: 'subject_name', visible: true, align: 'center', valign: 'middle'},
        {title: '是否为原始样本', field: 'extracted', visible: true, align: 'center', valign: 'middle'},
        {title: '领用人', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '领用量', field: 'usage_amount', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
SampleUsed.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SampleUsed.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加样本领用
 */
SampleUsed.openAddSampleUsed = function () {
    var index = layer.open({
        type: 2,
        title: '添加样本领用',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sampleUsed/sampleUsed_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看样本领用详情
 */
SampleUsed.openSampleUsedDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '样本领用详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sampleUsed/sampleUsed_update/' + SampleUsed.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除样本领用
 */
SampleUsed.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sampleUsed/delete", function (data) {
            Feng.success("删除成功!");
            SampleUsed.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sampleUsedId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询样本领用列表
 */
SampleUsed.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SampleUsed.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SampleUsed.initColumn();
    var table = new BSTable(SampleUsed.id, "/sampleUsed/list", defaultColunms);
    table.setPaginationType("client");
    SampleUsed.table = table.init();
});
