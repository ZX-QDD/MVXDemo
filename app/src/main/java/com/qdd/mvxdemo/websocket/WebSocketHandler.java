package com.qdd.mvxdemo.websocket;

import android.util.Log;

import androidx.annotation.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * 1、通过getInstance获取Handler实例
 * 2、通过setSocketIOCallBack设置回调
 * 3、通过connect进行连接，reConnect进行重连
 * 4、通过send发送消息，close关闭连接，cancel释放资源
 */
public class WebSocketHandler extends WebSocketListener {

    private final String wsUrl;

    private WebSocket webSocket;

    //记录连接状态
    private ConnectStatus status;

    private OkHttpClient client = new OkHttpClient.Builder()
            .build();

    private WebSocketHandler(String wsUrl) {
        this.wsUrl = wsUrl;
    }

    //单例handler对象
    private static WebSocketHandler INST;

    public static WebSocketHandler getInstance(String url) {
        if (INST == null) {
            synchronized (WebSocketHandler.class) {
                INST = new WebSocketHandler(url);
            }
        }

        return INST;
    }

    private WebSocketCallBack mSocketIOCallBack;

    public void setSocketIOCallBack(WebSocketCallBack callBack) {
        mSocketIOCallBack = callBack;
    }

    public void removeSocketIOCallBack() {
        mSocketIOCallBack = null;
    }

    public ConnectStatus getStatus() {
        return status;
    }

    /**
     * 连接
     */
    public void connect() {
        //构造request对象
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();

        webSocket = client.newWebSocket(request, this);
        status = ConnectStatus.Connecting;
    }

    /**
     * 重连
     */
    public void reConnect() {
        if (webSocket != null) {
            webSocket = client.newWebSocket(webSocket.request(), this);
        }
    }

    /**
     * 发送
     * @param text
     */
    public void send(String text) {
        if (webSocket != null) {
            Log.e("send",text);
            webSocket.send(text);
        }
    }

    /**
     * 释放WebSocket持有的资源，丢弃任何已经入队的消息
     */
    public void cancel() {
        if (webSocket != null) {
            webSocket.cancel();
        }
    }

    /**
     * 断开连接
     */
    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, null);
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.e("onOpen","已打开，可传输消息");
        this.status = ConnectStatus.Open;
        if (mSocketIOCallBack != null) {
            mSocketIOCallBack.onOpen();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        if (mSocketIOCallBack != null) {
            mSocketIOCallBack.onMessage(text);
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        this.status = ConnectStatus.Closing;
        Log.e("onClosing","正在关闭");
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        Log.e("onClosed","已关闭");
        this.status = ConnectStatus.Closed;
        if (mSocketIOCallBack != null) {
            mSocketIOCallBack.onClose();
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        Log.e("onFailure" , t.toString());
        t.printStackTrace();
        this.status = ConnectStatus.Canceled;
        if (mSocketIOCallBack != null) {
            mSocketIOCallBack.onConnectError(t);
        }
    }

    public enum ConnectStatus {
        Connecting, // the initial state of each web socket.
        Open, // the web socket has been accepted by the remote peer
        Closing, // one of the peers on the web socket has initiated a graceful shutdown
        Closed, //  the web socket has transmitted all of its messages and has received all messages from the peer
        Canceled // the web socket connection failed
    }

}
