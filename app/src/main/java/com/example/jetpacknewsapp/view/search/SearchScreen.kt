package com.example.jetpacknewsapp.view.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.view.Dimensions.MediumPadding1
import com.example.jetpacknewsapp.view.common.ArticleList
import com.example.jetpacknewsapp.view.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    searchEvent: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1)
            .padding(start = MediumPadding1)
            .padding(end = MediumPadding1)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { searchEvent(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                searchEvent(
                    SearchEvent.SearchNews
                )
            })

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let { it ->
            ArticleList(modifier = Modifier, articles = it.collectAsLazyPagingItems()) {
                navigateToDetails(it)
            }
        }
    }
}