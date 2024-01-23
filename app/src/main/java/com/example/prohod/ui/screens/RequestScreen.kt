package com.example.prohod.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prohod.R
import com.example.prohod.ui.components.HeaderLogo
import com.example.prohod.ui.theme.ButtonBase
import com.example.prohod.ui.theme.Cyan
import com.example.prohod.ui.theme.TextStyleMedium
import com.example.prohod.ui.theme.TextStyleSmall
import com.example.prohod.ui.viewmodels.SendRequestViewModel
import com.example.prohod.ui.viewmodels.StatusViewModel
import com.example.prohod.utils.Resource

@Preview
@Composable
private fun Preview() {
    RequestScreen(navController = rememberNavController())
}

@Composable
fun RequestScreen(navController: NavHostController) {

    val sendRequestViewModel = hiltViewModel<SendRequestViewModel>()
    val statusViewModel = hiltViewModel<StatusViewModel>()

    sendRequestViewModel.isSendRequest.observeAsState(Resource.success(false)).value?.let {
        it.data?.let { result ->
            if (result) {
                statusViewModel.setSkipRequestScreen()
                navController.navigate(MainNav.GenerateQRScreen.TAG) {
                    popUpTo(MainNav.RequestScreen.TAG) {
                        inclusive = true
                    }
                }
            }
        }
    }

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
            onClick = {
                sendRequestViewModel.sendRequest(
                    "Иван",
                    "Иванов",
                    "Иванович",
                    "6522 686868",
                    "2023-01-12T12:11:17.635Z",
                    "МВД России по Свердловской обл.",
                    "2024-01-23T12:11:17.635Z",
                    "05fd16d6-d17a-40a7-9c63-aa81e1ccc266",
                    "Сдать проект",
                    "ivanivanovich@gmail.com"
                )
            },
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
        chapter = 3, chapterHeader = "Адрес электронной почты", items = listOf(
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
