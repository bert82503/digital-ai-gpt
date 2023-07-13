package com.digital.gpt.chat.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OpenAI数据扩展入参
 * <p></p>
 * 额外参数json格式，在接收消息时会全部返回
 *
 * @author lihuagang
 * @date 2023/5/11
 */
@Data
@Accessors(chain = true)
public class OpenAiDataExtParam {
    /**
     * 会话身份（可以是开发者的用户身份或者其他）
     */
    private String chatId;
    /**
     * 请求身份
     * <p></p>
     * 分布式链路追踪身份
     */
    private String requestId;
    /**
     * 业务身份，可选
     */
    private String bizId;
}
