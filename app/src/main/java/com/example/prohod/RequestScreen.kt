package com.example.prohod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prohod.ui.ButtonBase
import com.example.prohod.ui.HeaderLogo
import com.example.prohod.ui.theme.Cyan
import com.example.prohod.ui.theme.TextStyleMedium
import com.example.prohod.ui.theme.TextStyleSmall

@Preview
@Composable
private fun Preview() {
    RequestScreen(navController = rememberNavController())
}

@Composable
fun RequestScreen(navController: NavHostController) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderLogo()
        Text(
            text = stringResource(id = R.string.fill_request),
            style = TextStyleMedium,
            modifier = Modifier
                .padding(start = 62.dp)
        )
        Chapters()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            var isChecked by remember { mutableStateOf(false) }
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier
                    .padding(end = 2.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = "даю согласие на обработку\nперсональных данных",
                style = TextStyleSmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        ButtonBase(
            onClick = { navController.navigate(MainNav.GenerateQRScreen.TAG) },
            text = "отправить заявку",
            modifier = Modifier
                .padding(vertical = 40.dp)
                .align(Alignment.CenterHorizontally)
                .size(width = 200.dp, height = 50.dp)
        )

    }

}

@Composable
private fun Chapters() {
    RequestChapter(
        chapter = 1,
        chapterHeader = "Паспортные данные",
        items = listOf(
            ChapterItem("Фамилия"),
            ChapterItem("Имя"),
            ChapterItem("Отчество"),
            ChapterItem("Серия и номер паспорта"),
            ChapterItem("Дата выдачи паспорта"),
            ChapterItem("Кем выдан паспорт")
        )
    )
    RequestChapter(
        chapter = 2,
        chapterHeader = "Детали визита",
        items = listOf(
            ChapterItem("Дата визита"),
            ChapterItem("К кому"),
            ChapterItem("Причина визита")
        )
    )
    RequestChapter(
        chapter = 3, chapterHeader = "Адрес эелектронной почты", items = listOf(
            ChapterItem("Адрес электронной почты")
        )
    )
}

@Composable
fun RequestChapter(chapter: Int, chapterHeader: String, items: List<ChapterItem>) {
    Column(Modifier.padding(vertical = 7.dp, horizontal = 16.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .size(32.dp)
                    .padding(top = 4.dp, bottom = 4.dp)
                    .drawBehind {
                        drawCircle(
                            color = Cyan,
                            radius = size.width / 2f
                        )
                        drawCircle(
                            color = Color.White,
                            radius = size.width / 2f,
                            style = Stroke(width = 1.dp.toPx())
                        )
                    },
                text = chapter.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp,
                ),
            )

            Text(
                text = chapterHeader, style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp,
                ), modifier = Modifier
                    .padding(start = 14.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        repeat(items.size) {
            val item = items[it]
            TextField(item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextField(item: ChapterItem) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(item.hint) },
        modifier = Modifier.padding(start = 46.dp, top = 20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            disabledBorderColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            disabledLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        )
    )
}

data class ChapterItem(
    val hint: String
)
