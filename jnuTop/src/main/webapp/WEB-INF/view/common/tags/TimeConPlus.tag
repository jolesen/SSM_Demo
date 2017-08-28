@/*
    时间查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
    isTime : 日期是否带有小时和分钟(true/false)
    pattern : 日期的正则表达式(例如:"YYYY-MM-DD")
@*/
<div class="form-group">
    <label class="col-sm-3 control-label" data-toggle="dropdown" class="btn btn-white dropdown-toggle">
        @if(isNotEmpty(icon)){
        <span class="${icon}"
              @if(isNotEmpty(iconStyle)){
              style="${iconStyle}"
              @}
        ></span>
        @}else{
        ${name}
        @}
    </label>
    <div class="col-sm-9">
        <input type="text" class="form-control layer-date"
               onclick="laydate({istime: ${isTime}, format: '${pattern}'})" id="${id}" placeholder="${name}" title="${name}"
               @if(isNotEmpty(value)){
               value="${tool.dateType(value)}"
               @}
        />
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}