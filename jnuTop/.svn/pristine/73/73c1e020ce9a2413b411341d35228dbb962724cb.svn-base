/**
 * 文库制备录入管理初始化
 */
var BuildLibrary = {
    id: "BuildLibraryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BuildLibrary.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '项目编码', field: 'projectCode', visible: true, align: 'center', valign: 'middle'},
        {title: '样本ID', field: 'sampleId', visible: true, align: 'center', valign: 'middle'},
        {title: '文库号', field: 'libraryCode', visible: true, align: 'center', valign: 'middle'},
        {title: '任务单名称', field: 'missionListName', visible: true, align: 'center', valign: 'middle'},
        {title: '96孔板位置', field: 'wellPlates96', visible: false, align: 'center', valign: 'middle'},
        {title: '回溶体积', field: 'redissolutionBulk', visible: false, align: 'center', valign: 'middle'},
        {title: '打断胶图位置', field: 'cutPositionImage', visible: false, align: 'center', valign: 'middle'},
        {title: '连接接头后浓度(ng/ul)', field: 'consistencyConnection', visible: false, align: 'center', valign: 'middle'},
        {title: '连接接头后总量', field: 'totalConnection', visible: false, align: 'center', valign: 'middle'},
        {title: 'INDEX标签7', field: 'index7', visible: true, align: 'center', valign: 'middle'},
        {title: 'INDEX标签5', field: 'index5', visible: true, align: 'center', valign: 'middle'},
        {title: '文库体积ul', field: 'libraryBulk', visible: true, align: 'center', valign: 'middle'},
        {title: 'pre-PCR循环数', field: 'loopPcrTimes', visible: false, align: 'center', valign: 'middle'},
        {title: 'QB浓度', field: 'qb', visible: false, align: 'center', valign: 'middle'},
        {title: '操作人', field: 'operater', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'},
        {title: '建库开始时间', field: 'buildStartDate', visible: true, align: 'center', valign: 'middle'},
        {title: '建库结束时间', field: 'buildEndDate', visible: true, align: 'center', valign: 'middle'},
        {
			title : '附件',
			field : 'attachment_path',
			visible : true,
			align : 'center',
			valign : 'middle',
			width : 60,
			formatter : function(value, row, index) {
				return [
					'<a href="javascript:void(0);" onclick="uploadAttachment(' + row.sample_id + ')" title="查看">上传</a>',
					'<br/><a href="javascript:void(0);" onclick="initDownloadAttachment(' + row.sample_id + ')" title="查看">下载</a>',

				].join('');
			}
		}
     ];
};

/**
 * 检查是否选中
 */
BuildLibrary.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BuildLibrary.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文库制备录入
 */
BuildLibrary.openAddBuildLibrary = function () {
    var index = layer.open({
        type: 2,
        title: '添加文库制备录入',
        area: ['800px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/BuildLibrary/BuildLibrary_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文库制备录入详情
 */
BuildLibrary.openBuildLibraryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文库制备录入详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/BuildLibrary/BuildLibrary_update/' + BuildLibrary.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文库制备录入
 */
BuildLibrary.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/BuildLibrary/delete", function (data) {
            Feng.success("删除成功!");
            BuildLibrary.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("BuildLibraryId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文库制备录入列表
 */
BuildLibrary.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BuildLibrary.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BuildLibrary.initColumn();
    var table = new BSTable(BuildLibrary.id, "/BuildLibrary/list", defaultColunms);
    table.setPaginationType("client");
    BuildLibrary.table = table.init();
});
