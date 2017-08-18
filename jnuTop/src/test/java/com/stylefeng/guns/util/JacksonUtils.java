package com.stylefeng.guns.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 在测试中操作生成JSON字符串的相关工具类
 * @author 梁俊鹏
 * @since 2017/8/16
 */
public class JacksonUtils {

    /**
     * 一个可以生成JSON字符串的方法
     * @author 梁俊鹏
     * @since 2017/8/16
     * @param obj 必须实现Serializable接口
     * @return 一个obj的JSON字符串
     */
    public static <T> String generateJacksonString(T obj) {

        ObjectMapper mapper = new ObjectMapper();
        String jackson = null;
        try {
            jackson = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.err.println("Cannot conver "+ obj.toString() +
                    " to JSON string.");
        }
        return jackson;
    }

    /**
     * 生成具有不完整信息的JSON字符串
     * @author 梁俊鹏
     * @since 2017/8/16
     * @param target the object that is referred to generate a JSON string
     * @param lackFieldName the name of the field that is missed in JSON string
     * @param <T> generic type
     * @return a JSON string without the specific field if success
     * @return a JSON string with all information in target if failed
     */
    public static <T> String generateJacksonString_withIncompleteInfo(T target, String lackFieldName) {

        String jackson = null;
        Class targetClass = target.getClass();
        Method[] methods = targetClass.getMethods();
        Method setterMethod = null, getterMethod = null;
        // 去除lackFieldName中多余的空格
        String lackFieldName_trim = lackFieldName.trim();

        for (Method method: methods) {
            String methodName = method.getName();
            if (methodName.substring(3).equalsIgnoreCase(lackFieldName_trim)) {
                if (methodName.startsWith("get")) {
                    getterMethod = method;
                } else if (methodName.startsWith("set")) {
                    setterMethod = method;
                } // end else if
            } // end if
        } // end for

        if (setterMethod == null || getterMethod == null) {
            System.err.println("Field name \"" + lackFieldName + // 使用原始输入
                    "\" cannot be removed from jackson string, " +
                    "because either getter or setter methods is not implemented.");
            return generateJacksonString(target);
        }

        try {
            Object temp = getterMethod.invoke(target);
            setterMethod.invoke(target, new Object[] { null });
            jackson = generateJacksonString(target);
            setterMethod.invoke(target, new Object[] { temp });
        } catch (Exception e) {
            jackson = generateJacksonString(target);
            System.err.println("Some error occurs while doing conversion, the result may be invalid");
            System.err.println("Getter: " + getterMethod.getName());
            System.err.println("Setter: " + setterMethod.getName());
            e.printStackTrace();
        }

        return jackson;
    }
}
