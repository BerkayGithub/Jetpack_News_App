package com.example.jetpacknewsapp.view.search

import androidx.paging.PagingData
import com.example.jetpacknewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
