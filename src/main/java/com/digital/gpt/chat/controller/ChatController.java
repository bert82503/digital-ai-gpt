package com.digital.gpt.chat.controller;

import com.digital.gpt.chat.common.tools.ValidatorUtils;
import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.common.tools.LoginContext;

import com.digital.gpt.chat.vo.rsp.ChatCompletionRsp;
import com.digital.gpt.chat.common.util.ParamUtil;
import com.digital.gpt.chat.model.ApiResultCode;
import com.digital.gpt.chat.service.ChatService;
import com.digital.gpt.chat.vo.req.ChatCompletionReq;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聊天对话模型
 * <pre>
 * 业务场景：
 * 润色
 * 生成摘要
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/13
 */
@Slf4j
@RestController("chatController")
@RequestMapping("/ai/gpt/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(
            ChatService chatService
    ) {
        this.chatService = chatService;
        log.info("create ChatController instance");
    }

    /**
     * 润色聊天对话
     * <pre>
     * 错误码：
     * 500-服务器繁忙
     * 600001-涉及敏感词
     * </pre>
     *
     * @param req 请求参数
     * @return 回复消息内容
     */
    @PostMapping("/completions")
    public ApiResult<ChatCompletionRsp> completions(
            @RequestHeader(LoginContext.KEY_ORG_ID) Long orgId,
            @RequestHeader(LoginContext.KEY_UID) Long userId,
            @RequestBody @Valid ChatCompletionReq req
    ) {
        try {
            // 参数校验
            ParamUtil.check(orgId, userId);
            ValidatorUtils.validateEntity(req);

            ChatCompletionRsp rsp = chatService.completions(orgId, userId, req);
            if (rsp == null) {
                return ApiResult.fail("completions Chat model fail");
            } else if (rsp.getMatchSensitiveWords() != null) {
                return ApiResult.fail(rsp.getMatchSensitiveWords(), ApiResultCode.MATCH_SENSITIVE_WORD);
            }
            return ApiResult.success(rsp);
        } catch (Exception e) {
            return ApiResult.fail(e.getMessage());
        }
    }
}
