package com.example.prohod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prohod.ui.HeaderLogo
import com.example.prohod.ui.theme.Background
import com.example.prohod.ui.theme.ProhodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            ProhodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {}
                NavHost(
                    navController = navController,
                    startDestination = MainNav.StartScreen.TAG
                ) {
                    composable(MainNav.StartScreen.TAG) {
                        StartScreen(navController)
                    }
                    composable(MainNav.RequestScreen.TAG) {
                        RequestScreen(navController)
                    }
                    composable(MainNav.GenerateQRScreen.TAG) {
                        GenerateQRScreen(navController)
                    }
                }
            }
        }
    }
}

sealed interface MainNav {
    val TAG: String

    object StartScreen : MainNav {
        override val TAG = "StartScreen"
    }

    object RequestScreen : MainNav {
        override val TAG = "RequestScreen"
    }

    object GenerateQRScreen : MainNav {
        override val TAG = "GenerateQRScreen"
    }
}