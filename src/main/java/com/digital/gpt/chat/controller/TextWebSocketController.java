package com.digital.gpt.chat.controller;

import com.digital.common.tools.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聊天智能生成
 *
 * @author lihuagang
 * @date 2023/5/13
 */
@Slf4j
@RestController("textWebSocketController")
@RequestMapping("/ai/gpt/web-socket/text")
public class TextWebSocketController {

    public TextWebSocketController() {
        log.info("create TextWebSocketController instance");
    }

    @PostMapping("/completions")
    public ApiResult<Void> completions() {
        return ApiResult.success(null);
    }
}
