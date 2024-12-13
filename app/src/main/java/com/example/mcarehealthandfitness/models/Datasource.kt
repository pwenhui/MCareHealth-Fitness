package com.example.mcarehealthandfitness.models

import com.example.mcarehealthandfitness.R

class Datasource {
    fun loadAffirmations(): List<ScrollableFitnessList> {
        return listOf<ScrollableFitnessList>(

            //fitness plan
            ScrollableFitnessList(R.string.muscle_build, R.drawable.back) ,
            ScrollableFitnessList(R.string.Yoga, R.drawable.yoga),
            ScrollableFitnessList(R.string.Cardio, R.drawable.jumprope),
        )
    }

    fun GlutenfreeRecipe():List<ScrollableRecipeList>{
        return listOf<ScrollableRecipeList>(

            //gluten free recipe list
            ScrollableRecipeList(R.string.Apple,R.string.recipe1,R.drawable.applecookies),
            ScrollableRecipeList(R.string.Salad,R.string.recipe2,R.drawable.kalecitrussalad),
            ScrollableRecipeList(R.string.pad_thai,R.string.recipe3,R.drawable.padthai),
            ScrollableRecipeList(R.string.rolls,R.string.recipe4,R.drawable.rolls),
            ScrollableRecipeList(R.string.tacos,R.string.recipe5,R.drawable.tacos),
            ScrollableRecipeList(R.string.chicken,R.string.recipe6,R.drawable.chickenbeans),
        )
    }

    fun LowCarbRecipe():List<ScrollableLowCarbList>{
        return listOf<ScrollableLowCarbList>(

            //gluten free recipe list
            ScrollableLowCarbList(R.string.veganpizza,R.string.carb1,R.drawable.pizzas),
            ScrollableLowCarbList(R.string.chickenandfeta,R.string.carb2,R.drawable.fetasalad),
            ScrollableLowCarbList(R.string.pomodoropasta,R.string.carb3,R.drawable.pomodoropasta),
            ScrollableLowCarbList(R.string.avocadosteak,R.string.carb4,R.drawable.steakwithavocado),
            ScrollableLowCarbList(R.string.greencurry,R.string.carb5,R.drawable.greencurry),
        )
    }
}