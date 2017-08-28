package com.stylefeng.guns.common.constant.state;

/**
 * 通知状态枚举类
 * @author zmj
 * @since 2017年8月24日上午11:26:15
 * 
 */
public enum NoticeStatus {

	UNREAD(0, "未读"), READ(1, "已读");
	int code;
	String message;

	NoticeStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (NoticeStatus s : NoticeStatus.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
