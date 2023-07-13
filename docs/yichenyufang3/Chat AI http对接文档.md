

Chat AI http对接文档（非流式输出）
======

此文档主要针对需要http对接的开发者，通过http对接操作简单不复杂，但不支持流式输出，可能会造成结果输出较长的情况。

以下将介绍http对接方式详情：

业务流程：开发者发消息到服务器-服务器收到消息后**异步处理**-处理完成后推送到开发者配置的通知地址
服务器地址：https://api.yichenyufang2.com/api/sendMsg
请求头：apiAppId(开发者申请的appId),apiSign：md5（appId+secret+chatId）
请求参数（body）：{
    msg：消息内容，
    chatId：会话id（可以是开发者的用户id或者其他），
    context：单个会话支持的上下文数量（0-角色最大上下文数量），
    maxTokens：单次回复最大字符数（10-角色最大单次回复数值），
    extParam：拓展参数（推送消息会传回）
}
返回参数（json）：code（1 成功，0 失败）
                msg（提示信息）
{"msg":"操作成功","code":1,"data":null}


当服务器将消息整合完后会推送到开发者配置的通知地址服务器。
推送规则：
http推送
参数：{
        code:1（目前只会传入1），
        msg：提示信息，
        data：{
            content：回复消息内容，
            chatId：发消息时传入的chatId，
            extParam：发消息时传入的extJson
        }
    }
重试规则：第1次-30s重试，第2次-5分钟重试，第3次-10分钟重试
开发者收到http推送后，需返回json格式，code=1即可

