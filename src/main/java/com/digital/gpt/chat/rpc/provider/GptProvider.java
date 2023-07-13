package com.digital.gpt.chat.rpc.provider;

import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.common.tools.ValidatorUtils;
import com.digital.gpt.chat.model.ApiResultCode;
import com.digital.gpt.chat.model.ChatGptMsg;
import com.digital.gpt.chat.model.ChatGptParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.function.Function;

/**
 * GPT服务提供者。
 *
 * @author lihuagang
 * @date 2023/5/31
 */
@Slf4j
public class GptProvider {

    /**
     * 应用gpt服务方法。
     *
     * @param param  入参
     * @param function 服务方法
     * @param methodName 服务方法名称
     * @return 出参
     */
    ApiResult<ChatGptMsg> applyGpt(
            ChatGptParam param, Function<ChatGptParam, ChatGptMsg> function, String methodName) {
        try {
            // 参数校验
            ValidatorUtils.validateEntity(param);

            ChatGptMsg msg = function.apply(param);
            if (msg == null) {
                return ApiResult.fail(methodName + " of GPT model fail");
            } else if (StringUtils.hasLength(msg.getMatchSensitiveWords())) {
                return ApiResult.fail(msg.getMatchSensitiveWords(), ApiResultCode.MATCH_SENSITIVE_WORD);
            }
            return ApiResult.success(msg);
        } catch (Exception e) {
            log.error("applyGpt error, param={}, methodName={}", param, methodName, e);
            return ApiResult.fail(e.getMessage());
        }
    }
}
