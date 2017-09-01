/**
 * 初始化文库制备录入详情对话框
 */
var BuildLibraryInfoDlg = {
    BuildLibraryInfoData : {},
    validateFields: {
        projectCode:{
            validators: {
                notEmpty: {
                    message: '项目编码不能为空'
                },
                stringLength: {
                    max: 20,
                    message: '项目编码长度不能超过20位'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9]+$/,
                    message: '项目编码只能包含数字、大写和小写字母'
                }
            }
        },
        sampleId:{
            validators: {
                notEmpty: {
                    message: '样本ID不能为空'
                },
                stringLength: {
                    max: 20,
                    message: '样本ID长度不能超过20位'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9]+$/,
                    message: '样本ID只能包含数字、大写和小写字母'
                }
            }
        },
        libraryCode:{
            validators: {
                notEmpty: {
                    message: '文库号不能为空'
                }
            }
        },
        missionListName:{
            validators: {
                notEmpty: {
                    message: '任务单名称不能为空'
                },
                stringLength: {
                    max: 15,
                    message: '受检者姓名长度不能超过15位'
                }
            }
        },
        operater:{
            validators: {
                notEmpty: {
                    message: '操作人不能为空'
                },
                stringLength: {
                    max: 15,
                    message: '操作人姓名长度不能超过15位'
                }
            }
        },
        buildStartDate:{
            validators: {
                date:{
                    format:'YYYY-MM-DD',
                    message:'日期格式不正确'
                }
            }
        },
        buildEndDate:{
            validators: {
                date:{
                    format:'YYYY-MM-DD',
                    message:'日期格式不正确'
                }
            }
        }
	}
};

/**
 * 清除数据
 */
BuildLibraryInfoDlg.clearData = function() {
    this.BuildLibraryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BuildLibraryInfoDlg.set = function(key, val) {
    this.BuildLibraryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BuildLibraryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BuildLibraryInfoDlg.close = function() {
    parent.layer.close(window.parent.BuildLibrary.layerIndex);
}

/**
 * 收集数据
 */
BuildLibraryInfoDlg.collectData = function() {
    this.set('projectCode').set('sampleId')
    .set('libraryCode').set('missionListName')
    .set('wellPlates96').set('redissolutionBulk')
    .set('consistencyConnection').set('totalConnection')
    .set('remarks').set('index7').set('index5')
    .set('libraryBulk').set('loopPcrTimes')
    .set('qb').set('operater').set('buildStartDate')
    .set('buildEndDate').set('id');
}

/**
 * 验证数据是否为空
 */
BuildLibraryInfoDlg.validate = function () {
    $('#BuildLibraryForm').data("bootstrapValidator").resetForm();
    $('#BuildLibraryForm').bootstrapValidator('validate');
    return $("#BuildLibraryForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
BuildLibraryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //数据验证
    if(!this.validate())
    {
    	return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/BuildLibrary/add", function(data){
        Feng.success("添加成功!");
        window.parent.BuildLibrary.table.refresh();
        BuildLibraryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.BuildLibraryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BuildLibraryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //数据验证
    if(!this.validate())
    {
    	return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/BuildLibrary/update", function(data){
        Feng.success("修改成功!");
        window.parent.BuildLibrary.table.refresh();
        BuildLibraryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.BuildLibraryInfoData);
    ajax.start();
}

$(function() {
	  Feng.initValidator("BuildLibraryForm", BuildLibraryInfoDlg.validateFields);
});
