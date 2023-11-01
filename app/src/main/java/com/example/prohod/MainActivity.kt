package com.example.prohod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prohod.ui.HeaderLogo
import com.example.prohod.ui.theme.Background
import com.example.prohod.ui.theme.ProhodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProhodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {}
                HeaderLogo()
                ProhodApp()
            }
        }
    }
}