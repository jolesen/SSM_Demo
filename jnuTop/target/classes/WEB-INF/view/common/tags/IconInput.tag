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
        <input class="form-control" id="${id}" name="${id}"
               @if(isNotEmpty(value)){
               value="${tool.dateType(value)}"
               @}
               @if(isNotEmpty(type)){
               type="${type}"
               @}else{
               type="text"
               @}
               @if(isNotEmpty(readonly)){
               readonly="${readonly}"
               @}
               @if(isNotEmpty(clickFun)){
               onclick="${clickFun}"
               @}
               @if(isNotEmpty(style)){
               style="${style}"
               @}
               @if(isNotEmpty(disabled)){
               disabled="${disabled}"
               @}
               @if(isNotEmpty(name)){
               title="${name}"
               @}
               @if(isNotEmpty(name)){
               placeholder="${name}"
               @}
               @if(isNotEmpty(list)){
               list="${list}"
               @}
        >
        @if(isNotEmpty(hidden)){
        <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
        @}

        @if(isNotEmpty(selectFlag)){
        <div id="${selectId}" style="display: none; position: absolute; z-index: 200;">
            <ul id="${selectTreeId}" class="ztree tree-box" style="${selectStyle!}"></ul>
        </div>
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}


