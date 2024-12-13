package com.example.mcarehealthandfitness.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomButtonLoader
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomDialogClose
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomOutlinedTextField
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomTopAppBar
import com.example.mcarehealthandfitness.viewmodel.RegistrationViewModel
import kotlinx.coroutines.launch

//Registration
@ExperimentalComposeUiApi
@Composable
fun Registration(
    navBack: () -> Unit,
    navHome: () -> Unit,
    vm: RegistrationViewModel = viewModel()
) {
    var showPassword by remember { mutableStateOf(false) }
    var showRegistrationError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    // Textfield Error State
    var emailError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var passwordMsg = "Required Field!"

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBar("Register New Account")
    }) {padding->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Spacer(Modifier.height(100.dp))

            Image(
                painter = painterResource(com.example.mcarehealthandfitness.R.drawable.m_care_logo),
                contentDescription = stringResource(com.example.mcarehealthandfitness.R.string.logo_desc),
                modifier = Modifier
                    .size(200.dp) // resize the image
            )

            //Application name
            Text("M. Care Health and Fitness",
                fontWeight = FontWeight.Bold,
                color = Color.Black)

            // Email
            CustomOutlinedTextField(
                labelText = stringResource(id = com.example.mcarehealthandfitness.R.string.email_field),
                textValue = vm.email,
                onValueChangeFun = {
                    vm.email = it
                    if (emailError) {
                        emailError = false
                    }
                },
                modifier = Modifier.padding(top = 8.dp),
                isSingleLine = true,
                errorState = emailError
            )

            //Password
            CustomOutlinedTextField(
                textValue = vm.password,
                modifier = Modifier.padding(top = 8.dp),
                labelText = stringResource(id = com.example.mcarehealthandfitness.R.string.password_field),
                onValueChangeFun = {
                    vm.password = it
                    if (passwordError) {
                        passwordError = false
                    }
                },
                isSingleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                errorState = passwordError,
                errorMsg = passwordMsg
            )

            Spacer(Modifier.height(30.dp))

            CustomButtonLoader(
                btnText = "Register",
                showLoader = isLoading,
                onClickFun = {
                    scope.launch {
                        isLoading = true

                        // Input validation - Checking Email Content
                        if (vm.email.isEmpty()) {
                            emailError = true
                            isLoading = false

                            // Display Snackbar
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Please fill in all required fields"
                            )
                            return@launch

                        } else {
                            emailError = false
                        }

                        // Input validation - Checking Password Content
                        if (vm.password.isEmpty()) {
                            passwordError = true
                            passwordMsg = "Required Field!"
                            isLoading = false

                            // Display Snackbar
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Please fill in all required fields"
                            )
                            return@launch

                        } else if (vm.password.length < 8) {
                            passwordError = true
                            passwordMsg = "Password should be at least 8 characters long!"
                            isLoading = false

                            // Display Snackbar
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Please check the Password field"
                            )
                            return@launch

                        } else {
                            passwordError = false
                        }

                        val data = vm.registerUser()

                        if (data != null) {
                            navHome() // Navigate to Login page
                        } else {
                            showRegistrationError = true
                            Log.println(Log.INFO, "Mcare", "Registration Failed")
                        }

                        isLoading = false
                    }
                },
                modifier = Modifier.padding(top = 24.dp)
            )
            if(showRegistrationError){
                CustomDialogClose(
                    alertTitle = "Registration Failed",
                    alertBody = "Error registering user. Please try again" ,
                    onDismissFun = { showRegistrationError=false },
                    btnCloseClick = { showRegistrationError=false })
            }
        }
    }
}