package com.example.mcarehealthandfitness.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcarehealthandfitness.ui.theme.widgets.*
import com.example.mcarehealthandfitness.viewmodel.LoginViewModel
import com.example.mcarehealthandfitness.viewmodel.UserProfileViewModel
import org.checkerframework.checker.units.qual.g

@Composable
fun CalorieCalculator(vm: UserProfileViewModel = viewModel()){
    var height by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("") }
    var bmr by remember { mutableStateOf("") }
    var tdee by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Enter your weight (in pounds):",modifier = Modifier.padding(5.dp), color = Color.Black)
        OutlinedTextField(
            value = vm.kg,
            onValueChange = {vm.kg = it},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Weight") }
        )

        Text("Enter your height (in inches):",modifier = Modifier.padding(5.dp), color = Color.Black)
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Height") }
        )
        Text("Enter your age:",modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = vm.age,
            onValueChange = { vm.age = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Age") }
        )
        Text("Enter your gender (M/F):",modifier = Modifier.padding(5.dp), color = Color.Black)

        OutlinedTextField(
            value = vm.gender,
            onValueChange = { vm.gender = it },
            label = { Text("Gender") }
        )
        Text("Enter your activity level (S/L/M):",modifier = Modifier.padding(5.dp), color = Color.Black)
        OutlinedTextField(
            value = activityLevel,
            onValueChange = { activityLevel = it },
            label = { Text("Activity Level") }
        )
        
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically)
        {
            Button(
                onClick = {
                    if (vm.kg.isNotEmpty() && height.isNotEmpty() && vm.age.isNotEmpty() && vm.gender.isNotEmpty() && activityLevel.isNotEmpty()) {
                        val weightInKg = vm.kg.toFloat() / 2.20462f
                        val heightInCm = height.toFloat() * 2.54f
                        val bmrResult = if (vm.gender.lowercase() == "m") {
                            88.362f + (13.397f * weightInKg) + (4.799f * heightInCm) - (5.677f * vm.age.toFloat())
                        } else {
                            447.593f + (9.247f * weightInKg) + (3.098f * heightInCm) - (4.330f * vm.age.toFloat())
                        }
                        bmr = String.format("%.0f", bmrResult)
                        val tdeeResult = when (activityLevel.lowercase()) {
                            "s" -> bmrResult * 1.2f
                            "l" -> bmrResult * 1.725f
                            "m" -> bmrResult * 1.55f
                            else -> 0f
                        }
                        tdee = String.format("%.0f", tdeeResult)
                    }
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Calculate")

            }

            Spacer(modifier = Modifier.weight(2f))

            if (bmr.isNotEmpty() && tdee.isNotEmpty()) {
                Column()
                {
                    Row()
                    {
                        Text("Your BMR is: $bmr",
                            color = Color.Black,
                            fontSize = 15.sp)
                    }

                    Row(){
                        Text("Your TDEE is: $tdee",
                            color = Color.Black,
                            fontSize = 15.sp)
                    }
                }

            }
        }

        Row()
        {

                ClickableText(text = AnnotatedString("Click here to see what to do with your TDEE"),

                    style = TextStyle(fontStyle = FontStyle.Italic,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        showDialog = true
                    }
                )
                if (showDialog){
                    CustomDialogCloseforTDEE(
                        alertTitle = "Calorie Maintenance: TDEE",
                        alertBody = "What it is and why is matters ? Checkout here TDEE ",
                        hyperlink = "TDEE",
                        onDismissFun = { showDialog = false },
                        btnCloseClick = { showDialog = false })
                }

        }
        Row()
        {
            ClickableText(text = AnnotatedString("Click here to see what to do with your BMR"),
                style = TextStyle(fontStyle = FontStyle.Italic,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                onClick = {
                    showDialog = true
                }
            )
            if (showDialog){
                CustomDialogCloseforBMR(
                    alertTitle = "Calorie Maintenance: BMR",
                    alertBody = "What it is and why is matters ? Checkout here BMR ",
                    hyperlink = "BMR",
                    onDismissFun = { showDialog = false },
                    btnCloseClick = { showDialog = false })
            }
        }
    }
}

@Composable
fun caloriecalculation(){
    //val navController = rememberNavController()
    var goals by remember { mutableStateOf("") }
    val amount_goals = goals.toDoubleOrNull()?:0.0

    var breakfast by remember{ mutableStateOf("") }
    val amount_breakfast = breakfast.toDoubleOrNull()?:0.0

    var lunch by remember{ mutableStateOf("") }
    val amount_lunch = lunch.toDoubleOrNull()?:0.0

    var dinner by remember{ mutableStateOf("") }
    val amount_dinner = dinner.toDoubleOrNull()?:0.0

    var exercise by remember{ mutableStateOf("") }
    val amount_exercise = exercise.toDoubleOrNull()?:0.0

    val food_total = calculate_food( amount_breakfast , amount_lunch, amount_dinner )

    val amount_remaining = calculate(amount_goals,amount_breakfast,amount_lunch,amount_dinner,amount_exercise)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(Color.White)
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)

    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0xFF238878),
            elevation = 7.dp
        ) {
            //Goal
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ){
                Text("Goal :",
                    fontSize =15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .width(50.dp)
                        .padding(top = 15.dp)
                )
                TextField(
                    value = goals,
                    onValueChange = {goals=it},
                    label = { Text(text = "Please enter your goal calorie here") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black),
                    singleLine = true
                )
                //AddData("Enter your breakfast calorie today")
            }
        }

        Spacer(Modifier.height(10.dp))

        //breakfast
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0xFF238878),
            elevation = 7.dp
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ){
                    AddData("Enter your breakfast calorie today",breakfast,{breakfast=it})
                }
            }
        }

        //Lunch
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0xFF238878),
            elevation = 7.dp
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ){
                    AddData("Enter your lunch calorie today",lunch,{lunch=it})
                }
            }
        }

        //dinner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0xFF238878),
            elevation = 7.dp
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ){
                    AddData("Enter your dinner calorie today",dinner,{dinner=it})
                }
            }
        }

        //exercise
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0xFF238878),
            elevation = 7.dp
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ){
                    AddData("Enter your calories burnt today",exercise,{exercise=it})
                }
            }
        }

        //food formula
        Spacer(Modifier.height(20.dp))
        Text(text = "Food  =  Breakfast + Lunch + Dinner",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp),
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)

        //display food result
        Spacer(Modifier.height(20.dp))
        Text(text = stringResource(id = com.example.mcarehealthandfitness.R.string.Food,food_total),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp),
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)

        //remaining calorie formula
        Spacer(Modifier.height(20.dp))
        Text(text = "Goal   -   Food    +  Exercise    =   Remaining Calorie",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)

        //display remaining calorie result
        Spacer(Modifier.height(20.dp))
        Text(text = stringResource(id = com.example.mcarehealthandfitness.R.string.Remaining_calorie,amount_remaining),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp),
            color = Color(0xFF4CA9EE),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold)

    }
    /*Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            CustomTopAppBar(title = "Calories Calculator")
        }, bottomBar = {BottomBar(navController = navController)}

    ) { padding->
        BottomNavGraph(navController = navController)

    }*/
}

//Do calculation of food
@Composable
fun calculate_food(amountB:Double,amountL:Double,amountD:Double)
        :Double{
    val food = amountB+amountL+amountD
    return food
}

//Do calculation of remaining calories
@Composable
fun calculate(amountG:Double,amountB:Double,amountL:Double,amountD:Double,amountE:Double)
        :Double{
    val food = amountB+amountL+amountD
    val remain = amountG - food + amountE
    return remain
}