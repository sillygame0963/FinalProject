package com.example.afinal

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.afinal.data.BottomNavItem
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

val items = listOf(
    BottomNavItem(
        title = "Home",
        icon = Icons.Rounded.Home
    ),

    BottomNavItem(
        title = "Wallet",
        icon = Icons.Rounded.Wallet
    ),

    BottomNavItem(
        title = "Notifications",
        icon = Icons.Rounded.Notifications
    ),

    BottomNavItem(
        title = "Account",
        icon = Icons.Rounded.AccountCircle
    )
)

@Composable
fun BottomNavBar(navController: NavHostController) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    when (item.title) {
                        "Home" -> navController.navigate("home")
                        "Wallet" -> navController.navigate("wallet")
                        "Notifications" -> navController.navigate("notifications")
                        "Account" -> navController.navigate("account")
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = rememberNavController())
}
