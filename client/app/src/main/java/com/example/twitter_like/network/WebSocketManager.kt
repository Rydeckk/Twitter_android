package com.example.twitter_like.network

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject

class WebSocketManager {
    private var socket: Socket? = null

    fun connect(userId: String, onMessageReceived: (String) -> Unit) {
        try {
            val options = IO.Options()
            options.transports = arrayOf("websocket")

            socket = IO.socket("http://10.0.2.2:3000", options)

            socket?.on(Socket.EVENT_CONNECT) {

                val joinRoom = JSONObject()
                joinRoom.put("event", "joinConversation")
                joinRoom.put("data", userId)
                socket?.emit("joinConversation", userId)
            }

            socket?.on("newMessage", Emitter.Listener { args ->
                val messageJson = (args[0] as JSONObject).toString()
                onMessageReceived(messageJson)
            })

            socket?.connect()
        } catch (e: Exception) {
            Log.e("WebSocket", "Erreur lors de la connexion: ${e.message}")
        }
    }

    fun sendMessage(conversationId: String, message: String) {
        if (socket?.connected() == true) {
            val messageJson = JSONObject()
            messageJson.put("conversationId", conversationId)
            messageJson.put("message", message)
            socket?.emit("sendMessage", messageJson)
        } else {
            Log.e("WebSocket", "Impossible d'envoyer le message : WebSocket non connecté")
        }
    }

    fun disconnect() {
        socket?.disconnect()
    }
}