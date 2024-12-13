package com.example.mcarehealthandfitness.ui

import com.example.mcarehealthandfitness.RegisterValidTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Saveprofilevalidate {

    @Test
    fun empty_name_return_false()
    {
        val saveProfile = RegisterValidTest()
        val result = saveProfile.checkValidField(
            "cheah",
            "20",
            "Happy",
            "Female",
            "50",
            "Diet 3 day"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun empty_age_return_false()
    {
        val saveProfile = RegisterValidTest()
        val result = saveProfile.checkValidField(
            "wenhui",
            "20",
            "Happy",
            "Female",
            "50",
            "Diet 3 day"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun empty_bio_return_false()
    {
        val saveProfile = RegisterValidTest()
        val result = saveProfile.checkValidField(
            "wenhui",
            "20",
            "Happy",
            "Female",
            "50",
            "Diet 3 day"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun empty_gender_return_false()
    {
        val saveProfile = RegisterValidTest()
        val result = saveProfile.checkValidField(
            "wenhui",
            "20",
            "Happy",
            "Female",
            "50",
            "Diet 3 day"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun empty_kg_return_false()
    {
        val saveProfile = RegisterValidTest()
        val result = saveProfile.checkValidField(
            "wenhui",
            "20",
            "Happy",
            "Female",
            "50",
            "Diet 3 day"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun empty_goal_return_false()
    {
        val saveProfile = RegisterValidTest()
        val result = saveProfile.checkValidField(
            "wenhui",
            "20",
            "Happy",
            "Female",
            "50",
            "Diet 3 days"
        )
        assertThat(result).isTrue()
    }
}