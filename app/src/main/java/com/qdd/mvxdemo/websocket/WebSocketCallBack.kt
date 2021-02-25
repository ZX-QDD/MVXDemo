package com.qdd.mvxdemo.websocket

interface WebSocketCallBack {

    fun onMessage(text: String)

    fun onOpen()

    fun onClose()

    fun onConnectError(s: Throwable)

}