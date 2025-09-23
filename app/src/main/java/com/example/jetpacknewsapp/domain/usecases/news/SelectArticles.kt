package com.example.jetpacknewsapp.domain.usecases.news

import com.example.jetpacknewsapp.data.local.NewsDAO
import com.example.jetpacknewsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDAO: NewsDAO
) {
    operator fun invoke(): Flow<List<Article>>{
        return newsDAO.getArticles()
    }
}