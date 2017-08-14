/**
 * 样本管理初始化
 */
var Sample = {
    id: "SampleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Sample.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
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
        Sample.seItem = selected[0];
        return true;
    }
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
    this.layerIndex = index;
};

/**
 * 点击上传附件
 */
Sample.uploadFile= function () {
    var index = layer.open({
        type: 2,
        title: '附件上传',
        area: ['1100px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sample/sample_uploadFile'
    });
    this.layerIndex = index;
};



/**
 * 打开查看样本详情
 */
Sample.openSampleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '样本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sample/sample_update/' + Sample.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除样本
 */
Sample.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sample/delete", function (data) {
            Feng.success("删除成功!");
            Sample.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sampleId",this.seItem.id);
        ajax.start();
    }
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
