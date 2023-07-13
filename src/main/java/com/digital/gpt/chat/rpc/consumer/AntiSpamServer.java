package com.digital.gpt.chat.rpc.consumer;

import com.digital.common.tools.result.ApiResult;
import com.digital.rpc.protocol.AceService;

/**
 * 反垃圾邮件服务
 * <pre>
 * 业务场景：
 * 敏感词检测
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/17
 */
@AceService(proxy = "AntiSpam", interfaceName = "AntiSpam")
public interface AntiSpamServer {
//    /**
//     * 检测敏感词
//     *
//     * @param message 文本消息
//     * @param force   是否强制(force固定传false)
//     * @param match   匹配的敏感词列表(英文逗号作为间隔符)
//     * @return 检测结果(0 - 表示没有敏感词 ， 1 - 表示有敏感词)
//     */
//    int checkMsg(String message, boolean force, @Out MutableString match);

    /**
     * 检测敏感词
     *
     * @param message 文本消息
     * @param force   是否强制(force固定传false)
     * @return 检测结果(0 - 表示没有敏感词 ， 1 - 表示有敏感词)
     */
    ApiResult<String> checkMsg(String message, boolean force);
}
