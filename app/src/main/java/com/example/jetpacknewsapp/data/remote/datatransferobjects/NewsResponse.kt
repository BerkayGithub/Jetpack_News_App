package com.example.jetpacknewsapp.data.remote.datatransferobjects

import com.example.jetpacknewsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)