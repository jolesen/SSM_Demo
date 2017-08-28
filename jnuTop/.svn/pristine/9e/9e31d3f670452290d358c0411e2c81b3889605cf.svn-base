/**
 * 初始化样本领用详情对话框
 */
var SampleUsedInfoDlg = {
    sampleUsedInfoData : {}
};

/**
 * 清除数据
 */
SampleUsedInfoDlg.clearData = function() {
    this.sampleUsedInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleUsedInfoDlg.set = function(key, val) {
    this.sampleUsedInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleUsedInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SampleUsedInfoDlg.close = function() {
    parent.layer.close(window.parent.SampleUsed.layerIndex);
}

/**
 * 收集数据
 */
SampleUsedInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
SampleUsedInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sampleUsed/add", function(data){
        Feng.success("添加成功!");
        window.parent.SampleUsed.table.refresh();
        SampleUsedInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleUsedInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SampleUsedInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sampleUsed/update", function(data){
        Feng.success("修改成功!");
        window.parent.SampleUsed.table.refresh();
        SampleUsedInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleUsedInfoData);
    ajax.start();
}

$(function() {

});
