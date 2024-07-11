package com.example.afinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.afinal.ui.theme.FinalTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalTheme {
                // Using the "background" color from a theme
                SetBarcolor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SetBarcolor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            WalletSection()
            CardSection()
            Spacer(modifier = Modifier.height(16.dp))
            FinanceSection()
            CurrencySection()
        }
    }
}

@Composable
fun HomeScreenPreview() {
    Scaffold(
        bottomBar = {
            BottomNavBarPreview()
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            WalletSection()
            CardSection()
            Spacer(modifier = Modifier.height(16.dp))
            FinanceSection()
            CurrencySection()
        }
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("wallet") {
            WalletScreen()
        }
        composable("notifications") {
            NotificationsScreen()
        }
        composable("account") {
            AccountScreen()
        }
    }
}

@Composable
fun WalletScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Wallet Screen")
    }
}

@Composable
fun NotificationsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Notifications Screen")
    }
}

@Composable
fun AccountScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Account Screen")
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    FinalTheme {
        HomeScreenPreview()
    }
}
