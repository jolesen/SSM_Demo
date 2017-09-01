/**
 * 样本领用管理初始化
 */
var SampleUsed = {
    id: "SampleUsedTable",	//表格id
    seItem: [],		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
SampleUsed.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: 'sampleId', field: 'sample_id', visible: false, align: 'center', valign: 'middle'},
        {title: '项目编号', field: 'detection_item', visible: true, align: 'center', valign: 'middle'},
        {title: '实验室编号', field: 'lab_code', visible: true, align: 'center', valign: 'middle'},
        {title: '样本编号', field: 'sample_number', visible: true, align: 'center', valign: 'middle'},
        {title: '受检人姓名', field: 'subject_name', visible: true, align: 'center', valign: 'middle'},
        {title: '是否为原始样本', field: 'extracted', visible: true, align: 'center', valign: 'middle'},
        {
        	title: '查看领用记录',
        	field: 'record',
        	visible: true,
        	align: 'center',
        	formatter: function(value, row, index) {
        		return [
'<a href="####" onclick="SampleUsed.openSampleUsedDetail(' + row.sample_id + ')" title="查看"><i class ="fa fa-search" aria-hidden="true"></i></a>'
        		        ].join('');
        	}
        }
        
       
    ];
};


/**
 * 检查是否选中
 */
SampleUsed.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	 for (var i = 0; i < selected.length; i++) {
    		 SampleUsed.seItem[i] = selected[i];
         }
        return true;
    }
};

/**
 * 点击添加样本领用
 */
SampleUsed.openAddSampleUsed = function () {
	if(this.check()) {
		 var array = new Array();
	    	for(var i = 0; i < this.seItem.length; i++){
				var id = this.seItem[i].sample_id
				array.push(id);
			}
			var ids = array.join(",");
			
		  var index = layer.open({
		        type: 2,
		        title: '添加样本领用',
		        area: ['800px', '420px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/sampleUsed/sampleUsed_add/'+ids
		    });
		    this.layerIndex = index;
	}
  
};

/**
 * 打开查看样本领用详情
 */
SampleUsed.openSampleUsedDetail = function (sample_id) {
    
        var index = layer.open({
            type: 2,
            title: '样本领用详情',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sampleUsed/sampleUsed_check/'+sample_id,
            success:function(a,b){
            
            }
        });
        this.layerIndex = index;
    
};

/**
 * 删除样本领用
 */
SampleUsed.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sampleUsed/delete", function (data) {
            Feng.success("删除成功!");
            SampleUsed.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sampleUsedId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 跳转到importFile页面
 */
SampleUsed.uploadFile = function () {
	  var index = layer.open({
          type: 2,
          title: '导入Excel',
          area: ['500px', '200px'], //宽高
          fix: false, //不固定
          maxmin: true,
          content: Feng.ctxPath + '/sampleUsed/sampleUsed_toImportFileUI'
      });
	
}

/**
 * 查询样本领用列表
 */
SampleUsed.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SampleUsed.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SampleUsed.initColumn();
    var table = new BSTable(SampleUsed.id, "/sampleUsed/list", defaultColunms);
    table.setPaginationType("client");
    SampleUsed.table = table.init();
});



