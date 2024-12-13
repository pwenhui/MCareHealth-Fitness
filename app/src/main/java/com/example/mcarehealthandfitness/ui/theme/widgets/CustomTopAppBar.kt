package com.example.mcarehealthandfitness.ui.theme.widgets

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun CustomTopAppBar_Back(title: String, navBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            CustomIconButton(
                onClickFun = navBack,
                icon = Icons.Filled.ArrowBack
            )
        }
    )
}

//without back arrow
@Composable
fun CustomTopAppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}
