package com.example.mcarehealthandfitness.ui.theme.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomDialog(
    alertTitle: String,
    alertBody: String,
    onDismissFun: () -> Unit,
    customBtn: @Composable () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissFun,
        title = { Text(alertTitle, fontWeight = FontWeight.ExtraBold) },
        text = { Text(text = alertBody) },
        buttons = customBtn,
    )
}

@Composable
fun CustomDialogClose(
    alertTitle: String,
    alertBody: String,
    onDismissFun: () -> Unit,
    btnCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    btnCloseText: String = "CLOSE",
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissFun,
        title = { Text(alertTitle, fontWeight = FontWeight.ExtraBold, color = Color.Black) },
        text = { Text(text = alertBody, color = Color.Black) },
        buttons = {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                CustomTextButton(
                    btnText = btnCloseText,
                    onClickFun = btnCloseClick,
                    btnTextColor = Color.Black
                )
            }
        },
    )
}

@Composable
fun CustomDialogCloseforTDEE(
    alertTitle: String,
    alertBody: String,
    hyperlink : String,
    onDismissFun: () -> Unit,
    btnCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    btnCloseText: String = "CLOSE",
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissFun,
        title = { Text(alertTitle, fontWeight = FontWeight.ExtraBold, color = Color.Black) },
        text = { HyperLinkText(fullText = alertBody, linkText = listOf(hyperlink), hyperlinks = listOf("https://lifesum.com/nutrition-explained/calorie-maintenance-what-it-is-and-why-it-matters") ) },
        buttons = {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                CustomTextButton(
                    btnText = btnCloseText,
                    onClickFun = btnCloseClick,
                    btnTextColor = Color.Black
                )
            }
        },
    )
}

@Composable
fun CustomDialogCloseforBMR(
    alertTitle: String,
    alertBody: String,
    hyperlink : String,
    onDismissFun: () -> Unit,
    btnCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    btnCloseText: String = "CLOSE",
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissFun,
        title = { Text(alertTitle, fontWeight = FontWeight.ExtraBold, color = Color.Black) },
        text = { HyperLinkText(fullText = alertBody, linkText = listOf(hyperlink), hyperlinks = listOf("https://www.leofitlabs.com/calorie-count-lose-weight-bmr-decrease-eat-less/#:~:text=Should%20you%20eat%20less%20than,physical%20activity%20and%20your%20BMR.") ) },
        buttons = {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                CustomTextButton(
                    btnText = btnCloseText,
                    onClickFun = btnCloseClick,
                    btnTextColor = Color.Black
                )
            }
        },
    )
}