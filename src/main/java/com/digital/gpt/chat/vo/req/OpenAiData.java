package com.digital.gpt.chat.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OpenAI数据入参
 *
 * @author lihuagang
 * @date 2023/5/11
 */
@Data
@Accessors(chain = true)
public class OpenAiData {
    /**
     * 发送端消息内容
     */
    private String msg;
    /**
     * api(固定值)
     */
    private String osType = "api";
    /**
     * 上下文关联长度，1-10
     */
    private int context;
    /**
     * 限制单次最大回复10-3500，不限制传空
     */
    private int limitTokens;
    /**
     * 额外参数json格式，在接收消息时会全部返回
     */
    private OpenAiDataExtParam extParam;
}
