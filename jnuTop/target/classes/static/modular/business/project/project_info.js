/**
 * 初始化项目管理详情对话框
 */
var ProjectInfoDlg = {
    projectInfoData : {}
};

/**
 * 清除数据
 */
ProjectInfoDlg.clearData = function() {
    this.projectInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProjectInfoDlg.set = function(key, val) {
    this.projectInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProjectInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProjectInfoDlg.close = function() {
    parent.layer.close(window.parent.Project.layerIndex);
}

/**
 * 收集数据
 */
ProjectInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
ProjectInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/project/add", function(data){
        Feng.success("添加成功!");
        window.parent.Project.table.refresh();
        ProjectInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.projectInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProjectInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/project/update", function(data){
        Feng.success("修改成功!");
        window.parent.Project.table.refresh();
        ProjectInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.projectInfoData);
    ajax.start();
}

$(function() {

});
