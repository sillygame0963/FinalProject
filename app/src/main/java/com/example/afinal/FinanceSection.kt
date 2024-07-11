package com.example.afinal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afinal.data.FinanceItem
import com.example.afinal.ui.theme.BlueStart
import com.example.afinal.ui.theme.GreenStart
import com.example.afinal.ui.theme.OrangeStart
import com.example.afinal.ui.theme.PurpleStart

val finance = listOf(
    FinanceItem(
        icon = Icons.Rounded.StarHalf,
        name = "Nguyen \nThai Son",
        background = OrangeStart
    ),

    FinanceItem(
        icon = Icons.Rounded.StarHalf,
        name = "Wallet",
        background = BlueStart
    ),

    FinanceItem(
        icon = Icons.Rounded.StarHalf,
        name = "Nguyen \nThai Son",
        background = PurpleStart
    ),

    FinanceItem(
        icon = Icons.Rounded.StarHalf,
        name = "Nguyen \nThai Son",
        background = GreenStart
    ),
)

@Preview
@Composable
fun FinanceSection() {
    Column {
        Text(
            text = "Finance",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(finance.size) {
                Item(it)
            }
        }
    }
}

@Composable
fun Item(
    index: Int
) {
    val FinanceList = finance[index]
    var lastPaddingEnd = 0.dp
    if (index == finance.size - 1) {
        lastPaddingEnd = 16.dp
    }

    Column (
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .size(120.dp)
            .clickable {}
            .padding(13.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(FinanceList.background)
            .padding(6.dp)
        ) {
            Icon(
                imageVector = FinanceList.icon,
                contentDescription = FinanceList.name,
                tint = Color.White
            )
        }

        Text(
            text = FinanceList.name,
            color = MaterialTheme.colorScheme.secondaryContainer,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}