package com.stylefeng.guns.common.constant.state;

/**
 * @Description 记录类型
 * @author wuliepeng
 * @since 2017/8/18 15:58
 */
public enum BizType {

    sample("样品");

    String message;

    BizType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
