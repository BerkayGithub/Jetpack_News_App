package com.example.jetpacknewsapp.view.onboarding

import android.media.Image
import androidx.annotation.DrawableRes
import com.example.jetpacknewsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pageList = listOf<Page>(
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem ipsum is simply dummy text of the printing and typesetting industry",
        image = R.drawable.onboarding3
    ),
)
