package com.example.jetpacknewsapp.domain.usecases.news

import com.example.jetpacknewsapp.data.local.NewsDAO
import com.example.jetpacknewsapp.domain.model.Article

class DeleteArticle(
    private val newsDAO: NewsDAO
) {
    suspend operator fun invoke(article: Article){
        newsDAO.delete(article)
    }
}