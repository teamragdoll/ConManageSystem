//package com.ragdoll.cloudmeeting.Test;
//
//
//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@ServerEndpoint("/websocket/{sid}")
//@Component
//public class WebSocketServer {
//
//
//    static Log log = LogFactory.getLog(WebSocketServer.class);
//    //静态变量，记录连接数
//    private static int onlineCount = 0;
//    //存放每个客户端对应的MyWebSocket对象
//    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
//
//    //与某个客户端的连接会话
//    private Session session;
//
//    //接受sid
//    private String sid = "";
//
//    //连接建立成功调用的方法
//    @OnOpen
//    public void onOpen(Session session, @PathParam("sid") String sid){
//        this.session = session;
//        webSocketSet.add(this);
//        addOnlineCount();
//        log.info("有新窗口开始监听：" + sid+",当前在线人数" + getOnlineCount());
//        this.sid=sid;
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("websocket IO异常");
//        }
//    }
//
//    //连接关闭调用的方法
//    @OnClose
//    public void onClose(){
//        webSocketSet.remove(this);
//        subOnlineCount();
//        log.info("有一连接关闭！当前人数为" + getOnlineCount());
//    }
//
//    //收到客户端消息后调用方法
//    @OnMessage
//    public void onMessage(String message, Session session){
//        log.info("收到来自窗口"+sid+"的消息：" + message);
//        //群发信息
//        for(WebSocketServer item : webSocketSet){
//            try{
//                item.sendMessage(message);
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //出错
//    @OnError
//    public void onError(Session session,Throwable error){
//        log.error("发生错误");
//        error.printStackTrace();
//    }
//
//    //实现服务器自动推送
//    public void sendMessage(String message) throws IOException {
//        this.session.getBasicRemote().sendText(message);
//    }
//
//    //群发自定义信息
//    public static void sendInfo(String message, @PathParam("sid") String sid){
//        log.info("推送消息到窗口" +sid+",推送内容："+message);
//        for(WebSocketServer item : webSocketSet){
//            try{
//                if(sid == null){
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage("连接成功");
//                }
//            }catch (IOException e){
//                continue;
//            }
//        }
//    }
//
//    public static synchronized void addOnlineCount() {
//        WebSocketServer.onlineCount++;
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocketServer.onlineCount--;
//    }
//
//}
