package com.example.afinal.ui.theme

import android.graphics.Bitmap
sealed class UIdesign {
    data class UpdatePrompt(val newPrompt: String) : UIdesign()
    data class SendPrompt(
        val prompt: String,
        val bitmap: Bitmap?
    ) : UIdesign()
}