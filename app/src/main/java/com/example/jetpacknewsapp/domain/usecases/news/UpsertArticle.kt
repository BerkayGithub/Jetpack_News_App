package com.example.jetpacknewsapp.domain.usecases.news

import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }

}