

Chat AI WebSocket流式输出对接（建议）
======


开发者申请API成功后，会返回appId和secret，用于接口对接。
具体对接方式如下：

我们支持WebSocket对接，客户端或者服务端可通过该方式与我们**保持连接**关系
连接地址：(https环境下) wss://socket.yichenyufang3.com/websocket/{flag}
(http环境) ws://socket.yichenyufang3.com/websocket/{flag}
参数说明：{flag}=appId+"_"+userId，即开发者的appId+业务方的用户唯一标识符或者某一个对话的id，该标识符必传且唯一。

当开发者通过以上方式连接成功后，即可调ws自带的sendMessage方法，传入参数param
param是由对象组装的json字符串，即：
param的值为：    {
    data:{
        msg:发送端消息内容，如：你好,
        osType:api(固定值),
        context:上下文关联长度，1-10,
        limitTokens:限制单次最大回复10-3500,不限制传空
        extParam:额外参数json格式，在接收消息时 会全部返回
    },
    sign:md5(data.msg+secret)，即对消息内容进行md5加密即可
}
以上即为发送消息的结构内容

ws返回说明(json格式)： {
    code: 1（成功），-5(账户字数已用完),
    msg: API答复的消息内容
    id: 消息id,
    extJson:发消息时传入的json格式参数
}
注：每一条完整的消息，ws返回的消息id是一样的，开发者可以通过消息id来判断该消息流属于哪一条。
且每一条完整的消息 都将会输出字符[DONE]，表示消息已接收完毕。

以上即为简单的对接方式，更多功能正在研发当中，如遇无法对接或遇到对接问题可以联系技术QQ：270915022

