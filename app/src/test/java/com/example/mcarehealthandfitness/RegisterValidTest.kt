package com.example.mcarehealthandfitness

class RegisterValidTest {


    fun checkValidField(name: String, age: String, Bio: String, Gender: String, Kg: String, goal: String): Boolean {

        // Input validation - Checking Name Content
        if (name.isEmpty()) {


            return false
        }

        // Input validation - Checking Age Content
        if (age.isEmpty()) {


            return false
        }

        else if (age == 0.toString()) {

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

        // Input validation - goal Content
        if (goal.isEmpty()){

            return false
        }

        return true
    }

}