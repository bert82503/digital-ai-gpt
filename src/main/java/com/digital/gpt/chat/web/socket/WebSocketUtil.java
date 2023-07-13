package com.digital.gpt.chat.web.socket;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * WebSocket辅助方法
 *
 * @author lihuagang
 * @date 2023/5/15
 */
public final class WebSocketUtil {

    private static final String DEFAULT_THREAD_NAME_PREFIX = "gpt.web.socket";

    /**
     * 创建一个新的WebSocket客户端
     */
    public static WebSocketClient applyWebSocketClient(String threadNamePrefix) {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        // 容器化场景下，返回的是容器/Pod使用的CPU核数，还是物理机的CPU核数？
//        int cpuNum = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setDaemon(true);
        if (StringUtils.hasText(threadNamePrefix)) {
            taskExecutor.setThreadNamePrefix(threadNamePrefix);
        } else {
            taskExecutor.setThreadNamePrefix(DEFAULT_THREAD_NAME_PREFIX);
        }
        taskExecutor.initialize();
        webSocketClient.setTaskExecutor(taskExecutor);
        return webSocketClient;
    }
}
