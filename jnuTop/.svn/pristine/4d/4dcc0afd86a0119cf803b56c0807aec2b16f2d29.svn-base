package com.stylefeng.guns.common.annotion.record;

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
     * 业务名称
     */
    String name() default "";

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"
     */
    String key() default "id";

}
