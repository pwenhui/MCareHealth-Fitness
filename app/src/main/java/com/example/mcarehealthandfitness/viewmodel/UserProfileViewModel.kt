package com.example.mcarehealthandfitness.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcarehealthandfitness.models.Users
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class UserProfileViewModel(): ViewModel() {
    private val _currentUser = MutableStateFlow(Users())
    private val currentUser: StateFlow<Users>
        get() = _currentUser

    var imageUri by mutableStateOf("")
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var bio by mutableStateOf("")
    var gender by mutableStateOf("")
    var kg by mutableStateOf("")
    var goals by mutableStateOf("")

    //VM Initialised
    init {
        viewModelScope.launch {
            if (!Users.currentUserId.isNullOrEmpty()) {
                _currentUser.value = Users.currentUserId?.let { Users.getCurrentUser(it) }!!

                name = currentUser.value.name
                age = currentUser.value.age.toString()
                bio = currentUser.value.bio
                gender = currentUser.value.gender
                kg = currentUser.value.kg.toString()
                goals = currentUser.value.goal

            }
        }
    }

    suspend fun updateProfile(): Boolean {
        val currentUser = Users.currentUserId?.let { Users.getCurrentUser(it) }

        return if (currentUser != null) {
            currentUser.name = name
            currentUser.age = age.toInt()
            currentUser.bio = bio
            currentUser.gender = gender
            currentUser.kg = kg.toInt()
            currentUser.goal = goals
            currentUser.imageprofile = imageUri
            Users.updateUserProfile(currentUser)
        } else {
            false
        }
    }


}