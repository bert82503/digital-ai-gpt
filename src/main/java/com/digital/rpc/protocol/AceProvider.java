package com.digital.rpc.protocol;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

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
@Component
public @interface AceProvider {

    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * 设置proxy默认是接口名称
     *
     * @return proxy
     */
    String proxy() default "";

    /**
     * 指定服务线程数，如果不指定默认使用全局线程池
     *
     * @return 服务线程数
     */
    int coreThreadNum() default 0;

    /**
     * 最大线程数，默认等于coreThreadNum
     * 如果是CPU任务最大不应该超过cpu数
     * 如果是IO任务建议不要超过4倍cpu数
     */
    int maxThreadNum() default 0;

    /**
     * 服务等待队列大小，和threadNum配和使用，默认是1000
     *
     * @return 服务等待队列大小
     */
    int maxQueueSize() default 0;

    /**
     * 绑定到具体的端口，server模式下需要指定
     *
     * @return 端口
     */
    int port() default 0;
}
