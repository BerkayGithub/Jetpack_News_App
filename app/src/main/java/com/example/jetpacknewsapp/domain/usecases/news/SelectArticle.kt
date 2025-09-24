package com.example.jetpacknewsapp.domain.usecases.news

import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }
}