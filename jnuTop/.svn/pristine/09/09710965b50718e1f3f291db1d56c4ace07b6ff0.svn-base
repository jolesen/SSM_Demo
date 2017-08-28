/**
 * 初始化样本提取详情对话框
 */
var SampleExtractedDnaInfoDlg = {
    sampleExtractedDnaInfoData : {}
};

/**
 * 清除数据
 */
SampleExtractedDnaInfoDlg.clearData = function() {
    this.sampleExtractedDnaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleExtractedDnaInfoDlg.set = function(key, val) {
    this.sampleExtractedDnaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleExtractedDnaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SampleExtractedDnaInfoDlg.close = function() {
    parent.layer.close(window.parent.SampleExtractedDna.layerIndex);
}

/**
 * 收集数据
 */
SampleExtractedDnaInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
SampleExtractedDnaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sampleExtractedDna/add", function(data){
        Feng.success("添加成功!");
        window.parent.SampleExtractedDna.table.refresh();
        SampleExtractedDnaInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleExtractedDnaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SampleExtractedDnaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sampleExtractedDna/update", function(data){
        Feng.success("修改成功!");
        window.parent.SampleExtractedDna.table.refresh();
        SampleExtractedDnaInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleExtractedDnaInfoData);
    ajax.start();
}

$(function() {

});
