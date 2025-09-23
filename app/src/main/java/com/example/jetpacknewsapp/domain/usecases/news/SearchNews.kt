package com.example.jetpacknewsapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>) : Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}