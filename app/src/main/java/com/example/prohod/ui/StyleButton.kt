package com.example.prohod.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prohod.ui.theme.ButtonTextStyle
import com.example.prohod.ui.theme.Cyan

@Composable
fun ButtonBase(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
) = Button(
    { onClick() },
    modifier = modifier.background(color = Cyan, shape = RoundedCornerShape(size = 100.dp)),
    colors = ButtonDefaults.buttonColors(containerColor = Cyan),
    content = {
        text?.let {
            Text(text = it, style = ButtonTextStyle)
        }
    }
)