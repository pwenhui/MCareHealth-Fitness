package com.example.mcarehealthandfitness.utils

import com.example.mcarehealthandfitness.R

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Fitness",
            image = com.example.mcarehealthandfitness.R.drawable.fitness_foreground,
            route = "fitness"
        ),
        BarItem(
            title = "Forum",
            image = com.example.mcarehealthandfitness.R.drawable.forum_foreground,
            route = "forum"
        ),
        BarItem(
            title = "Home",
            image = com.example.mcarehealthandfitness.R.drawable.ic_user_foreground,
            route = "home"
        ),
        BarItem(
            title = "Recipe",
            image = com.example.mcarehealthandfitness.R.drawable.recipe_foreground,
            route = "recipe"
        ),
        BarItem(
            title = "Calories",
            image = com.example.mcarehealthandfitness.R.drawable.calculate_foreground,
            route = "calories"
        )

    )
}