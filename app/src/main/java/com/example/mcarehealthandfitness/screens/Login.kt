package com.example.mcarehealthandfitness.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.models.Users
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomButtonLoader
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomDialogClose
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomOutlinedTextField
import com.example.mcarehealthandfitness.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@ExperimentalComposeUiApi
@Composable
fun Login(vm: LoginViewModel = viewModel(), navRegistration: () -> Unit, navProfile: () -> Unit){
    var showPassword by remember { mutableStateOf(false) }
    var showLoginError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var isLoading by remember{ mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter= painterResource(com.example.mcarehealthandfitness.R.drawable.m_care_logo),
            contentDescription = stringResource(com.example.mcarehealthandfitness.R.string.logo_desc),
            // resize image
            modifier = Modifier
                .size(200.dp)
        )

        //Application name
        Text(text = "M. Care Health and Fitness",
            fontWeight = FontWeight.Bold, color= Color.Black)

        //Email
        CustomOutlinedTextField(
            labelText = stringResource(id = com.example.mcarehealthandfitness.R.string.email_field),
            textValue =vm.email,
            onValueChangeFun = {vm.email = it},
            modifier = Modifier.padding(top = 8.dp),
            isSingleLine = true
        )

        //Password
        CustomOutlinedTextField(
            labelText = stringResource(com.example.mcarehealthandfitness.R.string.password_field),
            textValue = vm.password,
            modifier = Modifier.padding(top = 8.dp),
            onValueChangeFun = {vm.password = it},
            isSingleLine = true,
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = { //show or hide password icon
                IconButton(onClick = { showPassword =! showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.CheckCircle else Icons.Filled.Check,
                        contentDescription = "Show password"
                    )
                }
            }
        )

        Spacer(Modifier.height(30.dp))

        CustomButtonLoader(
            btnText="Login",
            showLoader = isLoading,
            onClickFun = {
                scope.launch {
                    isLoading=true
                    val data = vm.logInWithEmail()

                    if(data!= null){
                        //Navigate to Profile page
                        Users.updateLoginUser()
                        navProfile()
                    }else
                    {
                       showLoginError = true
                    }
                    isLoading = false
                }
            },
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = com.example.mcarehealthandfitness.R.string.registration),
            textDecoration = TextDecoration.Underline,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .clickable {
                    navRegistration()
                }
        )
        if (showLoginError){
            //login error dialog
            CustomDialogClose(
                alertTitle = stringResource(com.example.mcarehealthandfitness.R.string.Login_error_header),
                alertBody = stringResource(com.example.mcarehealthandfitness.R.string.Login_error_description),
                onDismissFun = { showLoginError = false },
                btnCloseClick = { showLoginError = false })
        }
    }
}