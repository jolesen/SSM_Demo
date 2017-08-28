package com.stylefeng.guns.core.aop;

import com.stylefeng.guns.common.annotion.log.BussinessLog;
import com.stylefeng.guns.common.annotion.record.BusinessRecord;
import com.stylefeng.guns.common.constant.dictmap.base.AbstractDictMap;
import com.stylefeng.guns.common.constant.dictmap.factory.DictMapFactory;
import com.stylefeng.guns.common.constant.state.BizType;
import com.stylefeng.guns.common.constant.state.RecordType;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.log.factory.LogTaskFactory;
import com.stylefeng.guns.core.record.factory.RecordTaskFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.Contrast;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wuliepeng
 * @Description 用于记录用户对业务的操作
 * @since 2017/8/18 9:16
 */
@Aspect
@Component
public class RecordAop {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.stylefeng.guns.common.annotion.record.BusinessRecord)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();
        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {
        //如果当前用户未登录，不做记录
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return;
        }

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        /*String methodName = currentMethod.getName();

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();*/

        //获取操作名称
        BusinessRecord annotation = currentMethod.getAnnotation(BusinessRecord.class);
        BizType bizType = annotation.bizType();
        RecordType recordType = annotation.recordType();
        String key = annotation.key();
        String seperator = annotation.seperator();

        /*StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }*/

        //如果涉及到修改,比对变化
        String msg;

        if (recordType.equals(RecordType.UPDATE)) {
            Object originalObj = LogObjectHolder.me().get();
            Map<String, String> modifyValue = HttpKit.getRequestParameters();
            msg = Contrast.contrastObjByName(bizType, key, seperator, originalObj, modifyValue);
            if (null != msg && !"".equals(msg)) {
                LogManager.me().executeLog(RecordTaskFactory.bizRecord(user.getAccount(), bizType, modifyValue.get(key), msg));
            }
        }
        /*if (bussinessName.indexOf("修改") != -1 || bussinessName.indexOf("编辑") != -1) {
            Object obj1 = LogObjectHolder.me().get();
            Map<String, String> obj2 = HttpKit.getRequestParameters();
            msg = Contrast.contrastObj(dictClass, key, obj1, obj2);
        } else {
            Map<String, String> parameters = HttpKit.getRequestParameters();
            AbstractDictMap dictMap = DictMapFactory.createDictMap(dictClass);
            msg = Contrast.parseMutiKey(dictMap,key,parameters);
        }*/
        //LogManager.me().executeLog(LogTaskFactory.bussinessLog(user.getId(), bussinessName, className, methodName, msg));
    }
}
