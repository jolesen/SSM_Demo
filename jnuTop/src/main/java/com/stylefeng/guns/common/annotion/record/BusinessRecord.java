package com.stylefeng.guns.common.annotion.record;

import com.stylefeng.guns.common.constant.state.BizType;
import com.stylefeng.guns.common.constant.state.RecordType;

import java.lang.annotation.*;

/**
 * @Description 标记需要做业务记录的方法
 *                业务记录：某人在某时间做了什么
 * @author wuliepeng
 * @since 2017/8/17 17:25
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BusinessRecord {

    /**
     * 业务类型
     */
    BizType bizType() default BizType.sample;

    /**
     * 记录类型
     */
    RecordType recordType() default RecordType.UPDATE;

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"
     */
    String key() default "id";

    /**
     * 分隔符
     */
    String seperator() default ";;;";
}
