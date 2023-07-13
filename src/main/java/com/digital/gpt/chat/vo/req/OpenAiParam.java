package com.digital.gpt.chat.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OpenAI请求入参
 *
 * @author lihuagang
 * @date 2023/5/11
 */
@Data
@Accessors(chain = true)
public class OpenAiParam {
    /**
     * 消息数据
     */
    private OpenAiData data;
    /**
     * md5(data.msg+secret)，即对消息内容进行md5加密即可
     */
    private String sign;
}
