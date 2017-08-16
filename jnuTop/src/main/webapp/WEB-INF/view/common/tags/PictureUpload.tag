@/*
图片上传参数的说明:
name : 名称
id : 头像的id
@*/
<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">
        @if(isNotEmpty(icon)){
        <span class="${icon}"
              @if(isNotEmpty(iconStyle)){
              style="${iconStyle}"
              @}
        ></span>
        @}
    </label>
    <div class="col-sm-4">
        <div id="${id}PreId" title="${name}">
            <div>
                <img width="100px" height="100px" src="${ctxPath}/static/img/webuploader.png">
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="head-scu-btn upload-btn" id="${id}BtnId">
            <i class="fa fa-upload"></i>&nbsp;上传
        </div>
    </div>
    <input type="hidden" id="${id}" value="${uploadImg!}"/>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
<div class="hr-line-dashed"></div>
@}


