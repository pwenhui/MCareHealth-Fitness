package com.example.mcarehealthandfitness.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.viewmodel.PostCreationViewModel
import com.example.mcarehealthandfitness.ui.theme.McareHealthandFitnessTheme
import com.example.mcarehealthandfitness.ui.theme.widgets.*
import com.example.mcarehealthandfitness.viewmodel.UserProfileViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Composable
fun PostCreation(vm: PostCreationViewModel= viewModel()){

    val ctx = LocalContext.current

    val notification = rememberSaveable { mutableStateOf("") }

    var loaderState by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    var nameError by rememberSaveable { mutableStateOf(false) }
    var titleError by rememberSaveable { mutableStateOf(false) }
    var descriptionError by rememberSaveable { mutableStateOf(false) }
    var ageMsg = "Required Field!"


    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp)
    ) {


        //author name
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Author",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(

                value = vm.name,
                onValueChange = {vm.name = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black)
            )
        }

        //Title
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Title",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.title,
                onValueChange = {vm.title = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black)
            )
        }

        //description
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Experience",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.description,
                onValueChange = {vm.description = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black)
            )
        }

        Spacer(Modifier.height(30.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            CustomButtonLoader(
                btnText = "Save",
                onClickFun={
                    scope.launch {
                        loaderState = true

                        // Input validation - Checking Name Content
                        if (vm.name.isEmpty()) {
                            nameError = true
                            loaderState = false

                            Toast.makeText(ctx, "Please fill in all required fields", Toast.LENGTH_SHORT).show()

                            return@launch
                        } else {
                            nameError = false
                        }

                        // Input validation - Checking Age Content
                        if (vm.title.isEmpty()) {
                            titleError = true
                            loaderState = false

                            Toast.makeText(ctx, "Please fill in all required fields", Toast.LENGTH_SHORT).show()

                            return@launch
                        } else {
                            titleError = false
                        }

                        // Input validation - Checking bio Content
                        if (vm.description.isEmpty()) {
                            descriptionError = true
                            loaderState = false


                            Toast.makeText(ctx, "Please fill in all required fields", Toast.LENGTH_SHORT).show()

                            return@launch
                        } else {
                            descriptionError = false
                        }

                        val status = vm.updateExperience()

                        if(status){

                            Toast.makeText(ctx, "Added a new post", Toast.LENGTH_SHORT).show()
                        }else{
                             Toast.makeText(ctx, "Unexpected error occurred. Please try again", Toast.LENGTH_SHORT).show()
                        }
                        loaderState = false

                    }
                },
                showLoader = loaderState,
                modifier = Modifier.clickable { notification.value = "User experience updated" },
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    McareHealthandFitnessTheme {

    }
    Surface {
        PostCreation()
    }}