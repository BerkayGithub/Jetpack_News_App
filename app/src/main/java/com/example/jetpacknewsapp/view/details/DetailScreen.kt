package com.example.jetpacknewsapp.view.details

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpacknewsapp.R
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.model.Source
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.view.Dimensions.ArticleImageHeight
import com.example.jetpacknewsapp.view.Dimensions.MediumPadding1
import com.example.jetpacknewsapp.view.details.components.DetailsTopBar

@Composable
fun DetailScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = article.url.toUri()
                    if (it.resolveActivity(context.packageManager) != null){
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null)
                        context.startActivity(it)
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item{
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(ArticleImageHeight).clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        R.color.text_title
                    )
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        R.color.body
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    JetpackNewsAppTheme(dynamicColor = false) {
        Surface(color = MaterialTheme.colorScheme.surface) {
            DetailScreen(
                article = Article(
                    author = "",
                    content = "As wildfires continue to have an impact across the world, BBC Tech Now’s Alasdair Keane travels to Canada to find out how new developments in technology are bringing hope of earlier detection and prevention.",
                    description = "How close are we to predicting wildfires with tech?",
                    publishedAt = "2 hours ago",
                    source = Source(
                        id = "",
                        name = "bbc",
                    ),
                    title = "How close are we to predicting wildfires with tech?",
                    url = "https://bbc.com/reel/video/p0m3mn2t/how-close-are-we-to-predicting-wildfires-with-tech- ",
                    urlToImage = "https://ichef.bbci.co.uk/images/ic/1920x1080/p0m4cy8t.jpg.webp"
                ),
                event = {}
            ) { }
        }
    }
}