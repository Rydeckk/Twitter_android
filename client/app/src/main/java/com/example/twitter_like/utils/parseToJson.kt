package com.example.twitter_like.utils
import com.example.twitter_like.data.model.message.Message
import com.google.gson.Gson

fun parseJsonToMessage(json: String): Message {
    return Gson().fromJson(json, Message::class.java)
}