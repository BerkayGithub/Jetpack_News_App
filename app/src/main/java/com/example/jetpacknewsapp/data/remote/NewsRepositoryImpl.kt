package com.example.jetpacknewsapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsAPI: NewsAPI
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsAPI = newsAPI,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}