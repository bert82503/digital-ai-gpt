package com.digital.gpt.chat.openai.impl;

import com.digital.common.tools.result.ApiResult;
import com.digital.gpt.chat.Application;
import com.digital.gpt.chat.rpc.consumer.AntiSpamServer;
import com.digital.gpt.chat.openai.CompletionRsp;
import com.digital.gpt.chat.openai.CompletionReq;
import com.digital.gpt.chat.openai.OpenAiService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

/**
 * Test of {@link OpenAiServiceImpl}.
 *
 * @author lihuagang
 * @date 2023/5/25
 */
@SpringBootTest(classes = Application.class)
//@SpringJUnitConfig(OpenAiServiceImpl.class)
class OpenAiServiceImplTest {

    @Resource
    private OpenAiService<CompletionReq, CompletionRsp> openAiService;
//    @Resource
//    private ModelRequestRecordService modelRequestRecordService;

    @MockBean
    private AntiSpamServer antiSpamServer;


    @Test
    void afterResponse() {
        CompletionReq req = new CompletionReq()
                .setRequestId("mockTest")
                .setOrgId(16L)
                .setUserId(666666666L);
        CompletionRsp rsp = new CompletionRsp()
                .setThirdPartyId("")
                .setReplyContent("mock test");
        // 准备-Given
        ApiResult<String> apiResult = ApiResult.success("ok");
        when(antiSpamServer.checkMsg(anyString(), anyBoolean()))
                .thenReturn(apiResult);

        // 执行-When
        openAiService.afterResponse(req, rsp);

        // 验证-Then
        verify(antiSpamServer, times(1))
                .checkMsg("mock test", false);
    }
}
