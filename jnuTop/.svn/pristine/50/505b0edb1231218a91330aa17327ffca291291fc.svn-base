/**
 * DNA样品提取管理初始化
 */
var SampleExtractedDna = {
    id: "SampleExtractedDnaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SampleExtractedDna.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '项目名称', field: 'detectionItem', visible: true, align: 'center', valign: 'middle'},
        {title: '样本编号', field: 'sampleNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '实验室编码', field: 'labCode', visible: true, align: 'center', valign: 'middle'},
        {title: '姓名', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
        {title: '样本类型', field: 'sampleType', visible: true, align: 'center', valign: 'middle'},
        {title: '提取人', field: 'extractPeopleId', visible: true, align: 'center', valign: 'middle'},
        {title: '提取日期', field: 'extractDate', visible: true, align: 'center', valign: 'middle'},
        {title: 'DNA存储位置', field: 'storageLocation', visible: true, align: 'center', valign: 'middle'},
        {title: 'NANODROP', field: 'nanodrop', visible: true, align: 'center', valign: 'middle'},
        {title: 'QUBIT', field: 'qubit', visible: true, align: 'center', valign: 'middle'},
        {title: 'OD260/280', field: 'od260280', visible: true, align: 'center', valign: 'middle'},
        {title: 'OD260/230', field: 'od260230', visible: true, align: 'center', valign: 'middle'},
        {title: '样图', field: 'image', visible: true, align: 'center', valign: 'middle'},
        {title: '是否完成', field: 'isComplete', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
SampleExtractedDna.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SampleExtractedDna.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加DNA样品提取
 */
SampleExtractedDna.openAddSampleExtractedDna = function () {
    var index = layer.open({
        type: 2,
        title: '添加DNA样品提取',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sampleExtractedDna/sampleExtractedDna_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看DNA样品提取详情
 */
SampleExtractedDna.openSampleExtractedDnaDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'DNA样品提取详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sampleExtractedDna/sampleExtractedDna_update/' + SampleExtractedDna.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除DNA样品提取
 */
SampleExtractedDna.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sampleExtractedDna/delete", function (data) {
            Feng.success("删除成功!");
            SampleExtractedDna.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sampleExtractedDnaId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询DNA样品提取列表
 */
SampleExtractedDna.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SampleExtractedDna.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SampleExtractedDna.initColumn();
    var table = new BSTable(SampleExtractedDna.id, "/sampleExtractedDna/list", defaultColunms);
    table.setPaginationType("client");
    SampleExtractedDna.table = table.init();
});
