package com.example.mcarehealthandfitness.models

import com.example.mcarehealthandfitness.R

class post {
    fun postexample(): List<ScrollablePostList> {
        return listOf<ScrollablePostList>(

            //post
            ScrollablePostList(R.string.title1,R.string.Author1,R.string.Content1,R.string.link2),
            ScrollablePostList(R.string.title2,R.string.Author2,R.string.Content2,R.string.link2) ,
            //ScrollablePostList(R.string.title2,R.string.Author2,R.string.Content2,R.string.link2)

        )
    }
}