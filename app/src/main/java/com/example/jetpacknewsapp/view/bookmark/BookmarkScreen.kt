package com.example.jetpacknewsapp.view.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpacknewsapp.R
import com.example.jetpacknewsapp.view.Dimensions.MediumPadding1
import com.example.jetpacknewsapp.view.common.ArticleList
import com.example.jetpacknewsapp.view.navgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = MediumPadding1, end = MediumPadding1, top = MediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.text_title)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticleList(articles = state.articles) {
            navigate(Route.DetailsScreen.route)
        }

    }
}