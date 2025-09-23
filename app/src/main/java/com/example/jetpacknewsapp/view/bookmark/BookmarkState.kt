package com.example.jetpacknewsapp.view.bookmark

import com.example.jetpacknewsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
