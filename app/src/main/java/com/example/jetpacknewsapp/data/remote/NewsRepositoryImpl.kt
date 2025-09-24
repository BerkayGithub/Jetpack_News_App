package com.example.jetpacknewsapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpacknewsapp.data.local.NewsDAO
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsAPI: NewsAPI,
    private val newsDAO: NewsDAO
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

    override fun searchNews(
        searchQuery: String,
        sources: List<String>,
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(
                    newsAPI = newsAPI,
                    sources = sources.joinToString(separator = ","),
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDAO.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDAO.delete(article)
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDAO.getArticle(url)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDAO.getArticles().onEach { it.reversed() }
    }
}