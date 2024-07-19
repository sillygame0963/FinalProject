package com.example.afinal

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.afinal.ui.theme.FinalTheme
import com.example.afinal.ui.theme.UIdesign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    private val uriState = MutableStateFlow<Uri?>(null)
    private val imagePicker = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let {
            uriState.update { it }
        }
    }

    private val isLoggedIn = MutableStateFlow(false)

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoggedIn.collectAsState().value) {
                        ChatScreen(paddingValues = PaddingValues())
                    } else {
                        LoginScreen(onLoginSuccess = { isLoggedIn.update { true } })
                    }
                }
            }
        }
    }


    @Composable
    fun ChatScreen(paddingValues: PaddingValues) {
        val chatViewModel: ChatViewModel = viewModel()
        val chatState = chatViewModel.chatState.collectAsState().value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding(), bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                reverseLayout = true
            ) {
                itemsIndexed(chatState.chatList) { index, chat ->
                    if (chat.isFromUser) {
                        UserChatItem(prompt = chat.prompt, bitmap = chat.bitmap)
                    } else {
                        ModelChatItem(response = chat.prompt)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    modifier = Modifier.weight(1f),
                    value = chatState.prompt,
                    onValueChange = {
                        chatViewModel.onEvent(UIdesign.UpdatePrompt(it))
                    },
                    placeholder = {
                        Text(text = "Type a prompt")
                    },
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable {
                            val bitmap = uriState.value?.let { uri ->
                                uriToBitmap(uri)
                            }
                            chatViewModel.onEvent(UIdesign.SendPrompt(chatState.prompt, bitmap))
                            uriState.update { null }
                        }
                        .padding(8.dp),
                    imageVector = Icons.Rounded.Send,
                    contentDescription = "Send prompt",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }

    @Composable
    fun UserChatItem(prompt: String, bitmap: Bitmap?) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                bitmap?.let {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(200.dp)
                            .padding(bottom = 2.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        bitmap = it.asImageBitmap()
                    )
                }

                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF006064))  // Dark teal color
                        .padding(16.dp),
                    text = prompt,
                    fontSize = 17.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // User Icon
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF006064))  // Dark teal color
                    .padding(8.dp),
                tint = Color.White
            )
        }
    }

    @Composable
    fun ModelChatItem(response: String) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            // Bot Icon
            Icon(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = "Bot Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(8.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFE1BEE7))  // Light purple color
                        .padding(16.dp),
                    text = response,
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap? {
        // Implement the conversion from Uri to Bitmap here
        // This is just a placeholder
        return null
    }
}
