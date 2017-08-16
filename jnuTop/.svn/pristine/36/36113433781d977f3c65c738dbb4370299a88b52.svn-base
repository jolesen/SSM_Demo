/**
 * 初始化样本详情对话框
 */
var SampleInfoDlg = {
	sampleInfoData : {},
    validateFields: {
        /*labCode:{
            validators: {
                notEmpty: {
                    message: '实验室编号不能为空'
                },
                stringLength: {
                    max: 20,
                    message: '实验室编号长度不能超过20位'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9]+$/,
                    message: '实验室编号只能包含数字、大写和小写字母'
                }
            }
        },*/
        sampleNumber:{
            validators: {
                notEmpty: {
                    message: '样本编号不能为空'
                },
                stringLength: {
                    max: 20,
                    message: '样本编号长度不能超过20位'
                },
                regexp: {
                    regexp: /^[a-zA-Z0-9]+$/,
                    message: '实验室编号只能包含数字、大写和小写字母'
                }
            }
        },
        detectionItem:{
            validators: {
                notEmpty: {
                    message: '检测项目不能为空'
                }
            }
        },
        subjectName:{
            validators: {
                notEmpty: {
                    message: '受检者不能为空'
                },
                stringLength: {
                    max: 15,
                    message: '受检者姓名长度不能超过15位'
                }
            }
        },
        salesman:{
            validators: {
                notEmpty: {
                    message: '销售员姓名不能为空'
                },
                stringLength: {
                    max: 15,
                    message: '销售员姓名长度不能超过15位'
                }
            }
        },
        detectionDuration:{
            validators: {
                regexp:{
                    regexp:/^\d+$/,
                    message:'检测所需时间只能是大于0的整数'
                }
            }
        }
	}
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
    this.set('sampleId').set('labCode').set('sampleNumber').set('detectionItem').set('salesman').set('subjectName')
        .set('acceptDate').set('detectionDuration').set('expectedReportTime').set('extracted').set('sampleType')
        .set('plasmaSeparation').set('transportCondition').set('sampleStorage').set('remark').set('picture');
}

/**
 * 验证数据是否为空
 */
SampleInfoDlg.validate = function () {
    $('#sampleInfoForm').data("bootstrapValidator").resetForm();
    $('#sampleInfoForm').bootstrapValidator('validate');
    return $("#sampleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
SampleInfoDlg.addSubmit = function() {
	this.clearData();
	this.collectData();

	// 判断是否能够提交
   /* if (!this.validate()) {
        return;
    }*/

	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/sample/add", function(data) {
		Feng.success("添加成功!");
		window.parent.Sample.table.refresh();
		SampleInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.sampleInfoData);
	ajax.start();
}

/**
 * 提交Excel
 */
SampleInfoDlg.uploadFile = function() {

	var formData = new FormData($("#formid")[0]);
		$.ajax({
			url : Feng.ctxPath + '/sample/importExcel',
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			dataType : "JSON",
			success : function(returndata) {
					Feng.success("导入成功!");
					window.parent.Sample.table.refresh();
					SampleInfoDlg.close();
					location.reload();
			},
			error : function(data) {
				 Feng.error("导入失败!" + data.responseJSON.message + "!");
			}
		});

}

/**
 * 提交修改
 */
SampleInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/sample/update", function(data) {
		Feng.success("修改成功!");
		window.parent.Sample.table.refresh();
		SampleInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.sampleInfoData);
	ajax.start();
}

function CheckFileType() {
    //是否上传成功
    var uploadSuccess = false;

    //是否检测成功
    var checkSuccess = false;

    //检测文件类型
        var file = $("#uploadFile").val();
        console.log("文件路径："+file);
        if(file == '' || file == null) {
            $("#hintInfo").html("<span style='color:red;'>请选择所要上传的文件！</span>");
            return false;
        }

        var index = file.lastIndexOf(".");
        var ext = file.substring(index + 1, file.length);
        console.log("文件类型："+ext);

        if(ext != "xlxs") {
            return false;
        }

        //获取文件名
        var index2 = file.lastIndexOf("\\");
        if(index < 0) {
            index2 = file.lastIndexOf("/");
        }
        var filename = file.substring(index2 + 1, file.length);
        console.log(filename);
}

$(function () {
    Feng.initValidator("sampleInfoForm", SampleInfoDlg.validateFields);

    var pictureUpload = new $WebUpload("picture");
    pictureUpload.setUploadBarId("progressBar");
    pictureUpload.init();

    //是否检测成功
    var checkSuccess = false;

    //检测文件类型
        var file = $("#uploadFile").val();
        console.log("文件路径："+file);
        if(file == '' || file == null) {
            $("#hintInfo").html("<span style='color:red;'>请选择所要上传的文件！</span>");
            return false;
        }

        var index = file.lastIndexOf(".");
        var ext = file.substring(index + 1, file.length);
        console.log("文件类型："+ext);

        if(ext != "xlxs") {
            return false;
        }

        //获取文件名
        var index2 = file.lastIndexOf("\\");
        if(index < 0) {
            index2 = file.lastIndexOf("/");
        }
        var filename = file.substring(index2 + 1, file.length);
        console.log(filename);
});
