package com.example.jetpacknewsapp.view.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpacknewsapp.R
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.model.Source
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.view.Dimensions.ArticleCardSize
import com.example.jetpacknewsapp.view.Dimensions.ExtraSmallPadding
import com.example.jetpacknewsapp.view.Dimensions.ExtraSmallPadding2
import com.example.jetpacknewsapp.view.Dimensions.SmallIconSize

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {

    val context = LocalContext.current

    Row(modifier = modifier.clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(horizontal = ExtraSmallPadding).height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(R.color.text_title)
                )
                Spacer(
                    modifier = Modifier.width(ExtraSmallPadding2)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(R.color.body)
                )
                Spacer(
                    modifier = Modifier.width(ExtraSmallPadding2)
                )
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(R.color.text_title)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview(){
    JetpackNewsAppTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            ArticleCard(
                article = Article(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "2 hours",
                    source = Source(id = "", name = "BBC"),
                    title = "Her traint broke down. Her phone died. And then she met her saviour in a",
                    url = "",
                    urlToImage = ""
                )
            ) { }
        }
    }
}