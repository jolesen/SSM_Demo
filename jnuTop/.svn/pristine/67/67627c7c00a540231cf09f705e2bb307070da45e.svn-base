/**
 * 业务记录管理初始化
 */
var Record = {
    id: "RecordTable",	//表格id
    table: null,
    layerIndex: -1
};

/**
 * 打开查看业务记录详情
 */
Record.openRecordDetail = function (bizType, bizId) {
    var index = layer.open({
        type: 2,
        title: '业务记录详情',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/record/detailPage/' + bizType + '/' + bizId,
    });
    this.layerIndex = index;
};

$(function () {

});

/**
 * 检查是否选中
 */
/*Record.check = function () {
 var selected = $('#' + this.id).bootstrapTable('getSelections');
 if(selected.length == 0){
 Feng.info("请先选中表格中的某一记录！");
 return false;
 }else{
 Record.seItem = selected[0];
 return true;
 }
 };*/

/**
 * 点击添加业务记录
 */
/*Record.openAddRecord = function () {
 var index = layer.open({
 type: 2,
 title: '添加业务记录',
 area: ['800px', '420px'], //宽高
 fix: false, //不固定
 maxmin: true,
 content: Feng.ctxPath + '/record/record_add'
 });
 this.layerIndex = index;
 };*/

/**
 * 删除业务记录
 */
/*Record.delete = function () {
 if (this.check()) {
 var ajax = new $ax(Feng.ctxPath + "/record/delete", function (data) {
 Feng.success("删除成功!");
 Record.table.refresh();
 }, function (data) {
 Feng.error("删除失败!" + data.responseJSON.message + "!");
 });
 ajax.set("recordId",this.seItem.id);
 ajax.start();
 }
 };*/

/**
 * 查询业务记录列表
 */
/*Record.search = function () {
 var queryData = {};
 queryData['condition'] = $("#condition").val();
 Record.table.refresh({query: queryData});
 };*/
