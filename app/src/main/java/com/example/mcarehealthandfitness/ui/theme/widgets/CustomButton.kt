package com.example.mcarehealthandfitness.ui.theme.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    btnText: String,
    onClickFun: () -> Unit,
    modifier: Modifier = Modifier,
    btnColor: Color = MaterialTheme.colors.primary,
    btnTextColor: Color = MaterialTheme.colors.onPrimary,
) {
    Button(
        onClick = onClickFun,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = btnColor,
            contentColor = btnTextColor
        ),
        modifier = modifier
            .width(150.dp)
            .height(50.dp),
    ) {
        Text(btnText, fontSize = 20.sp)
    }
}

@Composable
fun CustomButtonLoader(
    btnText: String,
    onClickFun: () -> Unit,
    showLoader: Boolean,
    modifier: Modifier = Modifier,
    btnColor: Color = Color(0xFF5ECD81), //light green
    btnTextColor: Color = Color.Black,
    fontSize: TextUnit = 20.sp,
) {
    TextButton(
        onClick = onClickFun,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = btnColor,
            contentColor = btnTextColor
        ),
        modifier = modifier
            .width(150.dp)
            .height(50.dp),
    ) {
        if (showLoader) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(text = btnText, fontSize = fontSize)
        }
    }
}

@Composable
fun CustomIconButton(onClickFun: () -> Unit, icon: ImageVector, btnDescription: String = "") {
    IconButton(onClick = onClickFun) {
        Icon(
            imageVector = icon,
            contentDescription = btnDescription
        )
    }
}

@Composable
fun CustomTextButton(
    btnText: String,
    onClickFun: () -> Unit,
    fontSize: TextUnit = MaterialTheme.typography.button.fontSize,
    modifier: Modifier = Modifier,
    btnColor: Color = Color(0xFF5ECD81), //light green
    btnTextColor: Color = Color.Black,
) {
    TextButton(
        onClick = onClickFun,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = btnColor)
    ){
        Text(btnText, fontSize = fontSize, color = btnTextColor)
    }
}


@Composable
fun CustomDialogBasic(
    alertTitle: String,
    alertBody: String,
    onDismissFun: () -> Unit,
    btnCancelClick: () -> Unit,
    btnAcceptClick: () -> Unit,
    modifier: Modifier = Modifier,
    btnAcceptText: String = "ACCEPT",
    btnCancelText: String = "CANCEL"
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissFun,
        title = { Text(alertTitle, fontWeight = FontWeight.ExtraBold) },
        text = { Text(text = alertBody) },
        buttons = {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                CustomTextButton(
                    btnText = btnCancelText,
                    onClickFun = btnCancelClick,
                    btnTextColor = MaterialTheme.colors.primary
                )
                CustomTextButton(
                    btnText = btnAcceptText,
                    onClickFun = btnAcceptClick,
                    btnTextColor = MaterialTheme.colors.primary
                )
            }
        },
    )
}

//for open up different type of challenge
@Composable
fun ButtonClick(
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor =  Color(0xFF4CA9EE)),
        onClick = {
            onButtonClick()
        }) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}
