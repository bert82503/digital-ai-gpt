package com.digital.gpt.chat.repository.entity;

import com.digital.gpt.chat.common.enumerate.ModelTypeEnum;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 模型请求记录信息表
 *
 * @author lihuagang
 * @date 2023/5/17
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_digital_ai_model_request_record")
public class ModelRequestRecord implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 组织身份
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 用户身份
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 模型类型，1-文本，2-聊天对话
     */
    @TableField(value = "model_type")
    private ModelTypeEnum modelType;

    /**
     * 请求身份
     */
    @TableField(value = "request_id")
    private String requestId;

    /**
     * 业务身份
     */
    @TableField(value = "biz_id")
    private String bizId;

    /**
     * 会话身份
     */
    @TableField(value = "chat_id")
    private String chatId;

    /**
     * 第三方身份
     */
    @TableField(value = "third_party_id")
    private String thirdPartyId;

    /**
     * 输入文本消息
     */
    @TableField(value = "input_message")
    private String inputMessage;

    /**
     * 回复消息内容
     */
    @TableField(value = "reply_content")
    private String replyContent;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
