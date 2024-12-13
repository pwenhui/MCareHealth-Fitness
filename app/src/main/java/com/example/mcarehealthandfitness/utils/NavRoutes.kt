package com.example.mcarehealthandfitness.utils

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Fitness : NavRoutes("fitness")
    object Forum : NavRoutes("forum")
    object Calories : NavRoutes("calories")
    object Recipe : NavRoutes("recipe")
}
