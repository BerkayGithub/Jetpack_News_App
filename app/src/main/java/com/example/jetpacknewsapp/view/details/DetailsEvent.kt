package com.example.jetpacknewsapp.view.details

import com.example.jetpacknewsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}