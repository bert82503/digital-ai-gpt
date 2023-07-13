

智能业务
======
> GPT-生成型预训练变换模型


# 工程介绍
工程结构使用应用分层

## 控制器层
### HTTP API入口
* [ChatController](src/main/java/com/digital/gpt/chat/controller/ChatController.java)
* [TextController](src/main/java/com/digital/gpt/chat/controller/TextController.java)
* [ChatWebSocketController](src/main/java/com/digital/gpt/chat/controller/ChatWebSocketController.java)
* [TextWebSocketController](src/main/java/com/digital/gpt/chat/controller/TextWebSocketController.java)

### RPC入口
> 上层业务使用
* [ChatGptServer](digital-ai-gpt-client/src/main/java/com/digital/gpt/chat/rpc/ChatGptServer.java)
* [TextGptServer](digital-ai-gpt-client/src/main/java/com/digital/gpt/chat/rpc/TextGptServer.java)


## 服务层
* [ChatService](src/main/java/com/digital/gpt/chat/service/ChatService.java)
* [TextService](src/main/java/com/digital/gpt/chat/service/TextService.java)
* [GptService](src/main/java/com/digital/gpt/chat/service/GptService.java) -GPT模型服务

#### OpenAI ChatGPT服务层
* [OpenAiService](src/main/java/com/digital/gpt/chat/openai/OpenAiService.java)

#### WebSocket
> WebSocket是一种在Web浏览器和服务器之间建立实时双向通信的技术。
> 适用于实时对话交互场景
* [WebSockets](https://docs.spring.io/spring-boot/docs/3.1.x/reference/html/messaging.html#messaging.websockets) -spring-boot
* [WebSockets](https://docs.spring.io/spring-framework/docs/5.3.24/reference/html/web.html#websocket) -spring-framework
  * [WebSocket API](https://docs.spring.io/spring-framework/docs/5.3.24/reference/html/web.html#websocket-server)
* [AI对话交互场景使用WebSocket建立H5客户端和服务端的信息实时双向通信](https://blog.csdn.net/shupili141005/article/details/130811504) -总结


## ChatGPT
### 面向开发者的 ChatGPT 提示工程
> https://github.com/GitHubDaily/ChatGPT-Prompt-Engineering-for-Developers-in-Chinese

为了帮助大家能更好的掌握 Prompt 工程，DeepLearning.ai 创始人吴恩达与 OpenAI 开发者 Iza Fulford 联手推出了一门面向开发者的技术教程：《ChatGPT 提示工程》。

### ChatGPT原理介绍
https://www.nolibox.com/creator_articles/principle_of_ChatGPT.html

