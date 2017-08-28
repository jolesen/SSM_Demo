/**
 * 项目管理管理初始化
 */
var Project = {
    id: "ProjectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Project.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '项目名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '预期所需天数', field: 'duration_time', visible: true, align: 'center', valign: 'middle'},
        {title: '项目状态', field: 'is_used', visible: true, align: 'center', valign: 'middle'},
        {title: '顺序', field: 'project_order', visible: true, align: 'center', valign: 'middle'},
        {title: '状态项目修改',field: 'status_change',formatter : operateFormatter}
    ];
};
//这是formatter对应的函数,添加两个按钮
function operateFormatter(value, row, index) {
    return ['<button  type="button" class="btn btn-primary btn-xs" onclick="Project.restore('+row.id+')" >启用</button> <button  type="button" class="btn btn-default btn-xs" onclick="Project.suspend('+row.id+')" >暂停</button>'
            ].join('');
    
}


/**
 * 检查是否选中
 */
Project.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Project.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加项目管理
 */
Project.openAddProject = function () {
    var index = layer.open({
        type: 2,
        title: '添加项目管理',
        area: ['800px', '260px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/project/project_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看项目管理详情
 */
Project.openProjectDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '项目管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/project/project_update/' + Project.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除项目管理
 */
Project.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/project/delete", function (data) {
            Feng.success("删除成功!");
            Project.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("projectId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 项目暂停
 */
Project.suspend = function (id) {
	var ajax = new $ax(Feng.ctxPath + "/project/suspend", function (data) {
        Feng.success("暂停成功!");
        Project.table.refresh();
    }, function (data) {
        Feng.error("暂停失败!" + data.responseJSON.message + "!");
    });
    ajax.set("projectId",id);
    ajax.start();
}

/**
 * 项目启用
 */
Project.restore = function (id) {

        var ajax = new $ax(Feng.ctxPath + "/project/restore", function (data) {
            Feng.success("启用成功!");
            Project.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set("projectId",id);
        ajax.start();
    
};

/**
 * 查询项目管理列表
 */
Project.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Project.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Project.initColumn();
    var table = new BSTable(Project.id, "/project/list", defaultColunms);
    table.setPaginationType("client");
    Project.table = table.init();
});
