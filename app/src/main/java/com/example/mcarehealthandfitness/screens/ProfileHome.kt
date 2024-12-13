package com.example.mcarehealthandfitness.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.R
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomButtonLoader
import com.example.mcarehealthandfitness.viewmodel.UserProfileViewModel
import kotlinx.coroutines.launch


@Composable
fun ProfileHome(vm: UserProfileViewModel = viewModel()){
    val ctx = LocalContext.current

    val notification = rememberSaveable { mutableStateOf("") }
    //val navController = rememberNavController()
    var loaderState by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    //TextField Error State
    var nameError by rememberSaveable { mutableStateOf(false) }
    var ageError by rememberSaveable { mutableStateOf(false) }
    var bioError by rememberSaveable { mutableStateOf(false) }
    var genderError by rememberSaveable { mutableStateOf(false) }
    var kgError by rememberSaveable { mutableStateOf(false) }
    var goalError by rememberSaveable { mutableStateOf(false) }
    var ageMsg = "Required Field!"
    var checkvalid by rememberSaveable { mutableStateOf(false) }
    var checkstatus by rememberSaveable { mutableStateOf(false) }


    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp)
    ) {

        // Image Upload
        ProfileImage()


        //name
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Name",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(

                value = vm.name,
                onValueChange = {vm.name = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black),
                singleLine = true
            )
        }

        //Age
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Age",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.age,
                onValueChange = {vm.age = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black),
                singleLine = true
            )
        }

        //bio
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Bio",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.bio,
                onValueChange = {vm.bio = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black),
                singleLine = true
            )
        }

        //Gender
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Gender",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.gender,
                onValueChange = {vm.gender = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black),
                singleLine = true
            )
        }

        //kg
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="Weight",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.kg,
                onValueChange = {vm.kg = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black),
                singleLine = true

            )
        }

        //goals
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text ="My goal",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = vm.goals,
                onValueChange = {vm.goals = it},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black),
                singleLine = true
            )
        }

        Spacer(Modifier.height(20.dp))

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

                        //check validation
                        checkvalid = true

                        if (checkstatus)
                        {
                            vm.updateProfile()
                            Toast.makeText(ctx, "Profile has been saved", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(ctx, "Unexpected error occurred. Please try again", Toast.LENGTH_SHORT).show()

                        }
                        loaderState = false
                    }
                },
                showLoader = loaderState,
                modifier = Modifier.clickable { notification.value = "Profile updated" },
            )

        }
        if (checkvalid)
        {
            if(checkValidField(name = vm.name, age = vm.age, Bio = vm.bio, Gender = vm.gender, Kg = vm.kg, goal = vm.goals))
            {
                checkstatus = true
            }
            else
            {
                Toast.makeText(ctx, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

@Composable
fun checkValidField(name: String, age: String, Bio: String, Gender: String, Kg: String, goal: String): Boolean {

    // Input validation - Checking Name Content
    if (name.isEmpty()) {

        return false
    }

    // Input validation - Checking Age Content
    if (age.isEmpty()) {

        return false
    } else if (age == 0.toString()) {

        return false
    }

    // Input validation - Checking bio Content
    if (Bio.isEmpty()) {

        return false
    }

    // Input validation - Checking gender Content
    if (Gender.isEmpty()) {

        return false
    }

    // Input validation - kilogram Content
    if (Kg.isEmpty()) {

        return false
    }

    if (goal.isEmpty()){


        return false
    }

    return true
}


@Composable
fun ProfileImage(vm: UserProfileViewModel = viewModel())
{

    val painter = rememberImagePainter(
        if (vm.imageUri.isEmpty()) {
            R.drawable.ic_user_foreground
        } else
            vm.imageUri
    )

    //launch an activity for us to select an image
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent() //get the content
    ){
            uri: Uri? -> //whatever uri we received
        uri.let { vm.imageUri = it.toString() } //we place it in our image value and above val painter will get updated with the new value (rememberImagePainter)
    }

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        //round image shape
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp) ){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
        Text(text = "Change Profile Picture",
            fontWeight = FontWeight.Bold,
            color = Color.Black,)
    }

}

