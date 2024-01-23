package com.example.prohod.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBase(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null
) = Button(
    { onClick() },
    modifier = modifier.background(color = Cyan, shape = RoundedCornerShape(size = 100.dp)),
    colors = ButtonDefaults.buttonColors(containerColor = Cyan),
    content = {
        text?.let {
            Text(text = it, style = TextStyleButton)
        }
    }
)

@Composable
fun ButtonOutlinedBase(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null
) = OutlinedButton({ onClick() }, 
    modifier = modifier.background(Background, RoundedCornerShape(size = 100.dp)),
    colors = ButtonDefaults.outlinedButtonColors(containerColor = Background)) {
    text?.let { 
        Text(text = it, style = TextStyleOutlinedButton)
    }
}