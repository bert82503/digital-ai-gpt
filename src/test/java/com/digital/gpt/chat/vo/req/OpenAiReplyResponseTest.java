package com.digital.gpt.chat.vo.req;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link OpenAiReplyResponse}.
 *
 * @author lihuagang
 * @date 2023/5/16
 */
class OpenAiReplyResponseTest {

    @Test
    void fromJson() {
        String jsonStr = "{\n" +
                "  \"msg\": \"Open\",\n" +
                "  \"code\": 1,\n" +
                "  \"extParam\": \"{\\\"requestId\\\":\\\"2fd50e2cd91a481eaf1f76f77b98709c\\\",\\\"chatId\\\":\\\"16:666666666\\\"}\",\n" +
                "  \"id\": \"chatcmpl-7Gg7BW8JJbHp6uJScRX7mJnv91rJH\"\n" +
                "}";
        OpenAiReplyResponse req = JSONUtil.toBean(jsonStr, OpenAiReplyResponse.class);
        assertThat(req).isNotNull();
        assertThat(req.getCode()).isEqualTo(1);
        assertThat(req.getMsg()).isEqualTo("Open");
        assertThat(req.getId()).isEqualTo("chatcmpl-7Gg7BW8JJbHp6uJScRX7mJnv91rJH");
//        String extParam = req.getExtParam();
//        assertThat(extParam).isNotEmpty();
//        OpenAiDataExtParam messageExtParam = JSONUtil.toBean(extParam, OpenAiDataExtParam.class);
//        assertThat(messageExtParam).isNotNull();
//        assertThat(messageExtParam.getRequestId()).isEqualTo("2fd50e2cd91a481eaf1f76f77b98709c");
//        assertThat(messageExtParam.getChatId()).isEqualTo("16:666666666");
    }
}
