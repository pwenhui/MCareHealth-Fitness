package com.example.mcarehealthandfitness.models

import android.net.Uri
import android.util.Log
import com.example.mcarehealthandfitness.models.Posts.Companion.updateUserPost
import com.example.mcarehealthandfitness.utils.DatabaseCollection
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class Users(
    val id: String = "",
    var imageprofile : String = "",
    var name: String = "",
    var gender: String="",
    var goal:String="",
    var age: Int = 0,
    var kg: Int = 0,
    var bio: String = "",
    var email: String = "",

) {
    companion object{

        val firestore = Firebase.firestore.collection(DatabaseCollection.USERS)
        var currentUserId = Firebase.auth.currentUser?.uid

        fun updateLoginUser(){
            currentUserId = Firebase.auth.currentUser?.uid
        }

        // Retrieve the User Profile from the database
        suspend fun getCurrentUser(userId: String): Users? {
            try {
                val data = firestore.document(userId).get().await()
                val currentUser: Users? = data.toObject<Users>()
                return currentUser
            } catch (ex: Exception) {
                Log.println(Log.INFO, "Mcare", ex.message.orEmpty())
                return null
            }
        }

        //update user profile
        suspend fun updateUserProfile(data:Users):Boolean{
            return try {
                firestore.document(data.id).set(data).await()
                true
            }catch (ex:Exception){
                Log.println(Log.INFO, "Mcare",ex.message.orEmpty())
                false
            }
        }


        // New User Registration
        suspend fun registerUser(userId: String, email: String) {
            try {

                val data = Users(
                    id = userId,
                    email = email,
                )
                updateUserProfile(data)

            } catch (ex: Exception) {
                Log.println(Log.INFO, "Mcare", ex.message.orEmpty())
            }
        }

    }
}
