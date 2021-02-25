package com.qdd.mvxdemo.websocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.qdd.mvxdemo.R
import kotlinx.android.synthetic.main.activity_web_socket.*

class WebSocketActivity : AppCompatActivity(),WebSocketCallBack {

    private val webSocketHandler by lazy { WebSocketHandler.getInstance("wss://echo.websocket.org") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_socket)
        webSocketHandler.setSocketIOCallBack(this)
        btn_connect.setOnClickListener {
            webSocketHandler.connect()
        }
        btn_send.setOnClickListener {
            webSocketHandler.send("你好ya！！")
        }
        btn_cancel.setOnClickListener {
            webSocketHandler.cancel()
        }
        btn_close.setOnClickListener {
            webSocketHandler.close()
        }
        btn_reConnect.setOnClickListener {
            webSocketHandler.reConnect()
        }
    }

    override fun onMessage(text: String) {
        Log.e("WebSocketActivity","onMessage：回声消息->$text")

    }

    override fun onOpen() {
    }

    override fun onClose() {
    }

    override fun onConnectError(s: Throwable) {
    }
}