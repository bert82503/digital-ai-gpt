
CREATE TABLE `t_digital_ai_model_request_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '组织身份',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户身份',
  `model_type` tinyint(4) unsigned DEFAULT '0' COMMENT '模型类型，1-文本，2-聊天对话',
  `request_id` varchar(128) DEFAULT NULL COMMENT '请求身份',
  `biz_id` varchar(128) DEFAULT NULL COMMENT '业务身份',
  `chat_id` varchar(128) DEFAULT NULL COMMENT '会话身份',
  `third_party_id` varchar(128) DEFAULT NULL COMMENT '第三方身份',
  `input_message` varchar(4096) DEFAULT NULL COMMENT '输入文本消息',
  `reply_content` varchar(4096) DEFAULT NULL COMMENT '回复消息内容',

  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_biz_id` (`biz_id`),
  KEY `idx_org_id_user_id` (`org_id`,`user_id`),
  KEY `idx_request_id` (`request_id`),
  KEY `idx_third_party_id` (`third_party_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='模型请求记录信息表';
