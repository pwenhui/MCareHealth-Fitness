package com.example.mcarehealthandfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.mcarehealthandfitness.screens.*
import com.example.mcarehealthandfitness.ui.theme.McareHealthandFitnessTheme
import com.example.mcarehealthandfitness.utils.NavigationRoutes
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            McareHealthandFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val currentUser = FirebaseAuth.getInstance().currentUser

                    NavigationController(currentUser)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NavigationController(currentUser: FirebaseUser?) {
    val startScreen: String =
        if (currentUser == null) NavigationRoutes.Login else NavigationRoutes.Home
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoutes.SplashScreen) {

        // SplashScreen
        composable(route = NavigationRoutes.SplashScreen) {
            splashscreen(navController, startUpRoute = startScreen)
        }

        // Login
        composable(route = NavigationRoutes.Login) {
            Login(
                navRegistration = { navController.navigate(NavigationRoutes.Registration) },
                navProfile = {
                    navController.navigate(NavigationRoutes.ProfileHome) {
                        navController.backQueue.clear()
                    }
                }
            )
        }

        //Registration
        composable(route = NavigationRoutes.Registration) {
            Registration(
                navBack = { navController.navigateUp() },
                navHome = { navController.navigate(NavigationRoutes.Login) }
            )
        }

        //ProfileHome
        composable(route = NavigationRoutes.ProfileHome) {
            MainScreen()

        }


    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultPreview() {
    McareHealthandFitnessTheme {
        val currentUser = FirebaseAuth.getInstance().currentUser
        NavigationController(currentUser)
    }
}

