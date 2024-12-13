package com.example.mcarehealthandfitness.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mcarehealthandfitness.models.Users
import com.example.mcarehealthandfitness.ui.theme.widgets.CustomTopAppBar
import com.example.mcarehealthandfitness.utils.NavBarItems
import com.example.mcarehealthandfitness.utils.NavRoutes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    Scaffold(
        topBar = {CustomTopAppBar(title = "M Care Health and Fitness")},
        content = {NavigationHost(navController = navController) },
        bottomBar = {BottomNavigationBar(navController = navController)}
    )
}

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        //profile
        composable(NavRoutes.Home.route) {
            ProfileHome()
        }
        //fitness
        composable(NavRoutes.Fitness.route) {
            ListofProgram()
        }
        //forum
        composable(NavRoutes.Forum.route) {
             communityPage()

        }

        //calories
        composable(NavRoutes.Calories.route) {
            CalorieCalculator()
        }
        //nutrition
        composable(NavRoutes.Recipe.route) {
            ListofRecipe()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(painter = painterResource(id = navItem.image),
                        modifier = Modifier.size(40.dp),
                        contentDescription = navItem.title,
                    )
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }

    }
}