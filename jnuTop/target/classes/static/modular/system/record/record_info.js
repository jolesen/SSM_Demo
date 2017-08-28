/**
 * 初始化表格的列
 *
 */
Record.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: false, visible: false},
        {
            title: '操作账户',
            field: 'userAccount',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return [
                    '<a href="javascript:void(0);" onclick="MgrUser.openUserDetail(\'' + row.userAccount + '\')" title="查看">' + row.userAccount + '</a>'
                ].join('');
            }
        },
        {title: '操作时间', field: 'createtime', align: 'center', valign: 'middle', sortable: true},
        {title: '操作信息', field: 'message', align: 'center', valign: 'middle'},
    ];
};

$(function () {
    var defaultColunms = Record.initColumn();
    var bizType = $("#bizType").val();
    var bizId = $("#bizId").val();
    var table = new BSTable(Record.id, '/record/detail/' + bizType + '/' + bizId, defaultColunms);
    table.setPaginationType("client");
    Record.table = table.init();
});
