package com.example.mcarehealthandfitness.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class LoginViewModel(): ViewModel() {
    //variables
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    //suspend function allow a function to start, pause and resume repeatedly
    suspend fun logInWithEmail(): AuthResult? {
        return try {
            val data = Firebase.auth
                .signInWithEmailAndPassword(email, password)
                .await()
            data
        } catch (e: Exception) {
            null
        }
    }
}