package com.digital.rpc.protocol;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 复制公共库代码，避免报错，忽略。
 *
 * @author lihuagang
 * @date 2023/7/13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AceService {
    /**
     * 设置proxy默认是接口名称
     *
     * @return proxy
     */
    String proxy() default "";

    /**
     * 设置interfaceName默认是接口名称
     *
     * @return interfaceName
     */
    String interfaceName() default "";
}
