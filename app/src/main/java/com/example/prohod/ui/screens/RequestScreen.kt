package com.example.prohod.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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

        var isCheckedPersonalData by remember { mutableStateOf(false) }
        val context = LocalContext.current

        HeaderLogo()
        Text(
            text = stringResource(id = R.string.fill_request),
            style = TextStyleMedium,
            modifier = Modifier.padding(start = 62.dp)
        )
        Chapters()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

            Checkbox(
                checked = isCheckedPersonalData,
                onCheckedChange = { isCheckedPersonalData = it },
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
                if (isCheckedPersonalData) {
                    sendRequestViewModel.sendRequest()
                } else {
                    Toast.makeText(
                        context,
                        "Необходимо согласие на обработку персональных данных",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
        chapter = 1, chapterHeader = "Паспортные данные", items = listOf(
            ChapterItem("Фамилия", ChapterTextField.SURNAME),
            ChapterItem("Имя", ChapterTextField.NAME),
            ChapterItem("Отчество", ChapterTextField.PATRONYMIC),
            ChapterItem("Серия и номер паспорта", ChapterTextField.PASSPORT_NUMBER_AND_SERIES),
            ChapterItem("Дата выдачи паспорта", ChapterTextField.PASSPORT_ISSUE_DATE),
            ChapterItem("Кем выдан паспорт", ChapterTextField.PASSPORT_ISSUED_BY)
        )
    )
    RequestChapter(
        chapter = 2, chapterHeader = "Детали визита", items = listOf(
            ChapterItem("Дата визита", ChapterTextField.VISIT_DATE),
            ChapterItem("К кому", ChapterTextField.WHO_TO_VISIT),
            ChapterItem("Причина визита", ChapterTextField.VISIT_REASON)
        )
    )
    RequestChapter(
        chapter = 3, chapterHeader = "Адрес электронной почты", items = listOf(
            ChapterItem("Адрес электронной почты", ChapterTextField.EMAIL)
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
                            color = Cyan, radius = size.width / 2f
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
    val requestViewModel = hiltViewModel<SendRequestViewModel>()
    val chapterTextFieldType = item.viewModelMapField
    val textState = requestViewModel.fieldToValue[chapterTextFieldType]?.observeAsState()

    val isNumeric = chapterTextFieldType == ChapterTextField.VISIT_DATE
            || chapterTextFieldType == ChapterTextField.PASSPORT_ISSUE_DATE
//            || chapterTextFieldType == ChapterTextField.PASSPORT_NUMBER_AND_SERIES

    OutlinedTextField(
        value = textState?.value ?: "",
        onValueChange = {
            requestViewModel.fieldToValue[chapterTextFieldType]?.value = it.trim()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isNumeric) {
                KeyboardType.Number
            } else if (chapterTextFieldType == ChapterTextField.EMAIL) {
                KeyboardType.Email
            } else {
                KeyboardType.Text
            },
            capitalization = if (chapterTextFieldType == ChapterTextField.EMAIL) {
                KeyboardCapitalization.None
            } else {
                KeyboardCapitalization.Sentences
            }
        ),
        label = { Text(item.hint) },
        placeholder = {
            if (chapterTextFieldType == ChapterTextField.PASSPORT_NUMBER_AND_SERIES) {
                Text(text = "6516 123456")
            } else if (chapterTextFieldType == ChapterTextField.PASSPORT_ISSUE_DATE || chapterTextFieldType == ChapterTextField.VISIT_DATE) {
                Text(text = "01.01.1970")
            }
        },
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
    val hint: String, val viewModelMapField: ChapterTextField
)

enum class ChapterTextField {
    NAME, SURNAME, PATRONYMIC, PASSPORT_NUMBER_AND_SERIES, PASSPORT_ISSUE_DATE, PASSPORT_ISSUED_BY, VISIT_DATE, WHO_TO_VISIT, VISIT_REASON, EMAIL
}
