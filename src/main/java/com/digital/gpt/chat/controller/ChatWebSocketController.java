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
@RestController("chatWebSocketController")
@RequestMapping("/ai/gpt/web-socket/chat")
public class ChatWebSocketController {

    public ChatWebSocketController() {
        log.info("create ChatWebSocketController instance");
    }

    @PostMapping("/completions")
    public ApiResult<Void> completions() {
        return ApiResult.success(null);
    }
}
