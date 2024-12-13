package com.example.mcarehealthandfitness.ui.theme.widgets

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.screens.PostCreation
import com.example.mcarehealthandfitness.viewmodel.PostCreationViewModel
import kotlinx.coroutines.launch

@Composable
fun DialogBox2FA(onDismiss: () -> Unit) {
    val contextForToast = LocalContext.current.applicationContext

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(color = Color(0xFF35898f)),
                    contentAlignment = Alignment.Center
                ) {

                    //insert the timer here
                    Timer_fitness(
                        totalTime = 100L * 1000L,
                        handleColor = Color.Green,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFF37B900),
                        modifier = Modifier.size(200.dp)
                    )

                }

            }
        }
    }
}
//back train
@Composable
fun dialogbox1(onDismiss: () -> Unit){
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = Color.White),
                    //contentAlignment = Alignment.Center
                ) {
                    fitCard(com.example.mcarehealthandfitness.R.drawable.dumbell,"BackTrain","Dumbell Deaflifts","3 sets / 6 reps")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = Color.White),
                    //contentAlignment = Alignment.Center
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    fitCard(com.example.mcarehealthandfitness.R.drawable.backextension,"BackTrain","Back Extensions","3 sets / 8 reps")
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = Color.White),
                    //contentAlignment = Alignment.Center
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    fitCard(com.example.mcarehealthandfitness.R.drawable.onearm,"BackTrain","One Arm Dumbell","3 sets / 6 reps")                }
            }
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun dialogbox2(onDismiss:() -> Unit){


    Dialog(onDismissRequest = {
        onDismiss()
    }){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            elevation = 4.dp
        ) {
            PostCreation()

        }
    }

}