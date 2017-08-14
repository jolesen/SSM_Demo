/**
 * 初始化样本详情对话框
 */
var SampleInfoDlg = {
    sampleInfoData : {}
};

/**
 * 清除数据
 */
SampleInfoDlg.clearData = function() {
    this.sampleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleInfoDlg.set = function(key, val) {
    this.sampleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SampleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SampleInfoDlg.close = function() {
    parent.layer.close(window.parent.Sample.layerIndex);
}

/**
 * 收集数据
 */
SampleInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
SampleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sample/add", function(data){
        Feng.success("添加成功!");
        window.parent.Sample.table.refresh();
        SampleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleInfoData);
    ajax.start();
}

/**
 * 提交附件
 */
SampleInfoDlg.uploadFile = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/excel", function(data){
    
    	//这里需要链接到controller
    	Feng.success("上传成功!");   	
    	 SampleInfoDlg.close();	
        
    },function(data){
        Feng.error("上传失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleInfoData);
    ajax.start();
}







/**
 * 提交修改
 */
SampleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sample/update", function(data){
        Feng.success("修改成功!");
        window.parent.Sample.table.refresh();
        SampleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sampleInfoData);
    ajax.start();
}

$(function() {

});
