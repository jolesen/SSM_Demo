@/*
表单中input框标签中各个参数的说明:

hidden : input hidden框的id
id : input框id
name : input框名称
readonly : readonly属性
clickFun : 点击事件的方法名
style : 附加的css属性
icon : input框的图标，使用glyphicon
iconStyle : icon所带的样式，如果icon没有值则不显示此样式
@*/
<div class="form-group">
    <label class="col-sm-3 control-label">
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
        <textarea class="form-control" id="${id}" name="${id}"
                  @if(isNotEmpty(value)){
                  value="${tool.dateType(value)}"
                  @}
                  @if(isNotEmpty(rows)){
                  rows="${rows}"
                  @}else{
                  rows="3"
                  @}
                  @if(isNotEmpty(cols)){
                  cols="${cols}"
                  @}
                  @if(isNotEmpty(disabled)){
                  disabled="${disabled}"
                  @}
                  @if(isNotEmpty(readonly)){
                  readonly="${readonly}"
                  @}
                  @if(isNotEmpty(clickFun)){
                  onclick="${clickFun}"
                  @}
                  @if(isNotEmpty(name)){
                  title="${name}"
                  placeholder="${name}"
                  @}
                  @if(isNotEmpty(style)){
                  style="${style}"
                  @}
        ></textarea>
        @if(isNotEmpty(hidden)){
        <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}


