package com.example.afinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.afinal.data.CardItem
import com.example.afinal.ui.theme.BlueEnd
import com.example.afinal.ui.theme.BlueStart
import com.example.afinal.ui.theme.GreenEnd
import com.example.afinal.ui.theme.GreenStart
import com.example.afinal.ui.theme.PurpleEnd
import com.example.afinal.ui.theme.PurpleStart
import com.example.afinal.ui.theme.OrangeEnd
import com.example.afinal.ui.theme.OrangeStart

val cards = listOf(
    CardItem (
        Type = "VISA",
        Number = "1220 3341 5568 7784",
        Name = "Nguyen Thai Son",
        Balance = 123.456,
        Color = getGradient(PurpleStart, PurpleEnd),
    ),

    CardItem (
        Type = "MASTER CARD",
        Number = "0081 7468 2920 5672",
        Name = "Nguyen Thai Son",
        Balance = 20.456,
        Color = getGradient(BlueStart, BlueEnd),
    ),

    CardItem (
        Type = "VISA",
        Number = "0987 2861 3298 0129",
        Name = "University",
        Balance = 12.789,
        Color = getGradient(OrangeStart, OrangeEnd),
    ),

    CardItem (
        Type = "MASTER CARD",
        Number = "9328 8236 1892 4378",
        Name = "cost of living",
        Balance = 2.250,
        Color = getGradient(GreenStart, GreenEnd),
    ),
)

fun getGradient(
    startColor: Color,
    endColor: Color
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            startColor,
            endColor
        )
    )
}

@Preview
@Composable
fun CardSection() {
    LazyRow {
        items(cards.size) {
            Card(it)
        }
    }
}

@Composable
fun Card(
    it: Int
) {
    val card = cards[it]
    var lastItemPaddingEnd = 0.dp
    if (it == cards.size - 1) {
        lastItemPaddingEnd = 16.dp
    }

    var image = painterResource(id = R.drawable.ic_visa)
    if (card.Type == "MASTER CARD") {
        image = painterResource(id = R.drawable.ic_mastercard)
    }
    Box(modifier = Modifier.padding(start = 16.dp, end = lastItemPaddingEnd)
    ) {
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(card.Color)
                .width(250.dp)
                .height(160.dp)
                .clickable {}
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = image,
                contentDescription = card.Name,
                modifier = Modifier.width(60.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = card.Name,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$ ${card.Balance}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = card.Number,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
