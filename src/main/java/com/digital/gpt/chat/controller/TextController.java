package com.digital.gpt.chat.controller;

import com.digital.gpt.chat.common.tools.LoginContext;
import com.digital.gpt.chat.common.tools.ValidatorUtils;
import com.digital.common.tools.result.ApiResult;

import com.digital.gpt.chat.vo.req.TextCompletionReq;
import com.digital.gpt.chat.common.util.ParamUtil;
import com.digital.gpt.chat.model.ApiResultCode;
import com.digital.gpt.chat.service.TextService;
import com.digital.gpt.chat.vo.rsp.TextCompletionRsp;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文本对话模型
 * <pre>
 * 业务场景：
 * 续写
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/13
 */
@Slf4j
@RestController("textController")
@RequestMapping("/ai/gpt/text")
public class TextController {

    private final TextService textService;

    public TextController(
            TextService textService
    ) {
        this.textService = textService;
        log.info("create TextController instance");
    }

    /**
     * 续写文本对话
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
    public ApiResult<TextCompletionRsp> completions(
            @RequestHeader(LoginContext.KEY_ORG_ID) Long orgId,
            @RequestHeader(LoginContext.KEY_UID) Long userId,
            @RequestBody @Valid TextCompletionReq req
    ) {
        try {
            // 参数校验
            ParamUtil.check(orgId, userId);
            ValidatorUtils.validateEntity(req);

            TextCompletionRsp rsp = textService.completions(orgId, userId, req);
            if (rsp == null) {
                return ApiResult.fail("completions Text model fail");
            } else if (rsp.getMatchSensitiveWords() != null) {
                return ApiResult.fail(rsp.getMatchSensitiveWords(), ApiResultCode.MATCH_SENSITIVE_WORD);
            }
            return ApiResult.success(rsp);
        } catch (Exception e) {
            return ApiResult.fail(e.getMessage());
        }
    }
}
