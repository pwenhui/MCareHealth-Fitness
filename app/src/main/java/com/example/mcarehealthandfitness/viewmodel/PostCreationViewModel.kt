package com.example.mcarehealthandfitness.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcarehealthandfitness.models.Posts
import com.example.mcarehealthandfitness.models.Users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.kg

class PostCreationViewModel():ViewModel() {

    private val _currentUser = MutableStateFlow(Posts())
    private val currentUser: StateFlow<Posts>
        get() = _currentUser

    var name by mutableStateOf("")
    var title by mutableStateOf("")
    var description by mutableStateOf("")


    init {
        viewModelScope.launch {
            ra = currentUser.value.areaid //43200
            name = currentUser.value.author
            title = currentUser.value.title
            description = currentUser.value.description

        }
    }

    suspend fun updateExperience(): Boolean {
        val currentUser = Posts.currentUserId?.let { Posts.getCurrentUser(it) }

        return if (currentUser != null) {
            currentUser.author = name
            currentUser.title = title
            currentUser.description = description
            Posts.updateUserPost(currentUser)
        } else {
            false
        }
    }

    private val _PostList = MutableStateFlow<List<Posts>>(emptyList())
    val postlist:StateFlow<List<Posts>>get()=_PostList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()

    private  var ra = currentUser.value.areaid

    fun refresh() {

        viewModelScope.launch {

            _isRefreshing.emit(true)
            _PostList.value = Posts.getBulletinList(ra)
            _isRefreshing.emit(false)
        }
    }

}