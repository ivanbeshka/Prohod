package com.example.prohod.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.prohod.R

val TextStyleMedium = TextStyle(
    fontSize = 32.sp,
    fontFamily = FontFamily(Font(R.font.gilroy_medium)),
    fontWeight = FontWeight(500),
    color = Color.White,
    textAlign = TextAlign.Center,
)

val TextStyleRegular = TextStyle(
    fontSize = 20.sp,
    fontFamily = FontFamily(Font(R.font.gilroy_regular)),
    fontWeight = FontWeight(400),
    color = Color.White,
    textAlign = TextAlign.Center,
)

val ButtonTextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = FontFamily(Font(R.font.gilroy_medium)),
    fontWeight = FontWeight(500),
    color = BlueDark,
    textAlign = TextAlign.Center
)