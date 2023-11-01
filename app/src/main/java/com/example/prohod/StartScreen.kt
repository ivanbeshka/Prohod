package com.example.prohod

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prohod.ui.ButtonBase
import com.example.prohod.ui.HeaderLogo
import com.example.prohod.ui.theme.Cyan
import com.example.prohod.ui.theme.TextStyleMedium
import com.example.prohod.ui.theme.TextStyleRegular

@Preview
@Composable
fun ProhodApp(
    navController: NavHostController = rememberNavController()
) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (textHello, textDesc, buttonNext) = createRefs()
        Text(
            stringWelcome,
            style = TextStyleMedium,
            modifier = Modifier.constrainAs(textHello) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            }
        )

        Text(
            text = stringResource(id = R.string.description),
            style = TextStyleRegular,
            modifier = Modifier
                .constrainAs(textDesc) {
                    top.linkTo(textHello.bottom)
                }
                .padding(top = 47.dp)
                .padding(horizontal = 60.dp)
        )

        ButtonBase(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(buttonNext) {
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(parent)
                }
                .padding(bottom = 68.dp)
                .width(200.dp)
                .height(50.dp),
            text = stringResource(id = R.string.go_to_rtf)
        )
    }
}

private val stringWelcome = buildAnnotatedString {
    withStyle(SpanStyle(Color.White)) {
        append("Добро пожаловать\nна платформу")
    }
    withStyle(SpanStyle(Cyan)) {
        append(" pro")
    }
    withStyle(SpanStyle(Color.White)) {
        append("ход!")
    }
}