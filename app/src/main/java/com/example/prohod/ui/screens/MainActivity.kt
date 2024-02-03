package com.example.prohod.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prohod.ui.theme.Background
import com.example.prohod.ui.theme.ProhodTheme
import com.example.prohod.ui.viewmodels.AuthViewModel
import com.example.prohod.ui.viewmodels.StatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val statusViewModel by viewModels<StatusViewModel>()
    private val authViewModel by viewModels<AuthViewModel>()

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
                    startDestination = if (!statusViewModel.getSkipStartScreen()) {
                        MainNav.StartScreen.TAG
                    } else if (!statusViewModel.getSkipRequestScreen()) {
                        MainNav.RequestScreen.TAG
                    } else {
                        MainNav.GenerateQRScreen.TAG
                    }
                ) {

                    if (!statusViewModel.getSkipStartScreen()) {
                        composable(MainNav.StartScreen.TAG) {
                            StartScreen(navController)
                        }
                    }

                    if (!statusViewModel.getSkipRequestScreen()) {
                        composable(MainNav.RequestScreen.TAG) {
                            RequestScreen(navController)
                        }
                    }

                    composable(MainNav.GenerateQRScreen.TAG) {
                        GenerateQRScreen(navController)
                    }
                }

                authViewModel.subscribeOnTokenUpdating()
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