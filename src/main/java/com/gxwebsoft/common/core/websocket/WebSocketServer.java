package com.gxwebsoft.common.core.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/api/chat/{userId}")
@Component
public class WebSocketServer {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
        }

        try {
            sendMessage(userId, "连接成功");
        } catch (IOException e) {

        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
        }
    }


    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String userId, String message) throws IOException {
        if (webSocketMap.containsKey(userId)) {
            Session session1 = webSocketMap.get(userId).session;
            if (session1 != null) session1.getBasicRemote().sendText(message);
        }
    }


    /**
     * 实现服务器主动推送
     */
    public void sendAllMessage(String message) throws IOException {
        ConcurrentHashMap.KeySetView<String, WebSocketServer> userIds = webSocketMap.keySet();
        for (String userId : userIds) {
            WebSocketServer webSocketServer = webSocketMap.get(userId);
            webSocketServer.session.getBasicRemote().sendText(message);
        }
    }

}
