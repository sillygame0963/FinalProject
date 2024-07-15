package com.example.afinal

import android.graphics.Bitmap
import com.example.afinal.data.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)