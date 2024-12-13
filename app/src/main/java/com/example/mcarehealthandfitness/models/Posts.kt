package com.example.mcarehealthandfitness.models

import android.util.Log
import com.example.mcarehealthandfitness.utils.DatabaseCollection
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlinx.parcelize.Parcelize


data class Posts(
    val id: String = "",
    val areaid: Int = 43200,
    var author: String = "",
    var title: String = "",
    var description: String = "",

){
    companion object {
        val firestore = Firebase.firestore.collection(DatabaseCollection.POSTS)

        var currentUserId = Firebase.auth.currentUser?.uid


        // Retrieve the User Profile from the database
        suspend fun getCurrentUser(userId: String): Posts? {
            try {
                val data = firestore.document(userId).get().await()
                val currentUser: Posts? = data.toObject<Posts>()
                return currentUser
            } catch (ex: Exception) {
                Log.println(Log.INFO, "Mcare", ex.message.orEmpty())
                return null
            }
        }

        //update user post
        suspend fun updateUserPost(data:Posts):Boolean{
            return try {
                firestore.document(data.id.toString()).set(data).await()
                true
            }catch (ex:Exception){
                Log.println(Log.INFO, "Mcare",ex.message.orEmpty())
                false
            }
        }

        // New User Registration
        suspend fun registerUserpost(AId: Int,UId:String) {
            try {

                val data = Posts(
                    areaid = AId,
                    id = UId
                )
                updateUserPost(data)

            } catch (ex: Exception) {
                Log.println(Log.INFO, "Mcare", ex.message.orEmpty())
            }
        }

        // Retrieve bulletin list by area code
        suspend fun getBulletinList(areacode: Int): List<Posts> {
            return try {

                val data = firestore.whereEqualTo("areaid", areacode).get().await()
                if (!data.isEmpty) {
                    data.toObjects()
                } else {
                    emptyList()
                }
            } catch (ex: Exception) {
                Log.println(Log.INFO, "NeighbourHub", ex.message.orEmpty())
                emptyList()
            }
        }

    }
}
