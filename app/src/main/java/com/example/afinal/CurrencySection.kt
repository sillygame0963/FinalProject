package com.example.afinal

import android.icu.util.Currency
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afinal.data.CurrencyItem
import com.example.afinal.ui.theme.GreenStart

val Currency = listOf(
    CurrencyItem(
        name = "USD",
        buy = 67.3f,
        sell = 67.3f,
        icon = Icons.Rounded.AttachMoney
    ),

    CurrencyItem(
        name = "VND",
        buy = 20f,
        sell = 50.43f,
        icon = Icons.Rounded.AttachMoney
    ),

    CurrencyItem(
        name = "YEN",
        buy = 34.67f,
        sell = 20.65f,
        icon = Icons.Rounded.AttachMoney
    ),

    CurrencyItem(
        name = "EUR",
        buy = 78f,
        sell = 88.12f,
        icon = Icons.Rounded.AttachMoney
    ),
)

@Preview
@Composable
fun CurrencySection() {
    var isVisible by remember {
        mutableStateOf(false)
    }

    var iconState by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowUp)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 32.dp),
    contentAlignment = Alignment.BottomCenter
    ) {
    Column (
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .animateContentSize()
    ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .animateContentSize()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        isVisible = !isVisible
                        if (isVisible) {
                            iconState = Icons.Rounded.KeyboardArrowDown
                        } else {
                            iconState = Icons.Rounded.KeyboardArrowUp
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = iconState,
                        contentDescription = "Currencies",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    modifier = Modifier,
                    text = "Currencies",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
            )

            if(isVisible) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val BoxWithConstraintsScope = this
                    val width = BoxWithConstraintsScope.maxWidth / 3

                    Column(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.width(width),
                                text = "Currencies",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                modifier = Modifier.width(width),
                                text = "Buy",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.End
                            )

                            Text(
                                modifier = Modifier.width(width),
                                text = "Sell",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.End
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyColumn {
                            items(Currency.size) { index ->
                                Item(
                                    index = index,
                                    width = width
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Item(index: Int, width: Dp) {
    val CurrencyItem = Currency[index]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.width(width),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(GreenStart)
                .padding(4.dp)
            ) {
                Icon(
                    imageVector = CurrencyItem.icon,
                    contentDescription = CurrencyItem.name,
                    modifier = Modifier.size(18.dp),
                    tint = Color.White

                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = CurrencyItem.name,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            modifier = Modifier.width(width).padding(start = 10.dp),
            text = "$ ${CurrencyItem.buy}",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End
        )

        Text(
            modifier = Modifier.width(width).padding(start = 10.dp),
            text = "$ ${CurrencyItem.sell}",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End
        )
    }
}