package com.example.jetpacknewsapp.view.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.jetpacknewsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    private val _searchState = mutableStateOf(SearchState())
    var searchState: State<SearchState> = _searchState

    fun onEvent(searchEvent: SearchEvent){
        when(searchEvent){
            is SearchEvent.UpdateSearchQuery -> {
                _searchState.value = searchState.value.copy(searchQuery = searchEvent.searchQuery)
            }
            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            searchQuery = searchState.value.searchQuery,
            sources = listOf("abc-news", "bbc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _searchState.value = searchState.value.copy(articles = articles)
    }
}