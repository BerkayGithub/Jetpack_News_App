package com.example.jetpacknewsapp.view.details.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpacknewsapp.R
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {},
        modifier = Modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onBackClick) {
                Icon(painter = painterResource(R.drawable.ic_back_arrow), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(painter = painterResource(R.drawable.ic_bookmark), contentDescription = null)
            }
            IconButton(onClick = onShareClick) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(painter = painterResource(R.drawable.ic_network), contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailTopBarPreview(){
    JetpackNewsAppTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            DetailsTopBar(
                onBrowsingClick = { },
                onShareClick = {},
                onBookmarkClick = {}
            ) { }
        }
    }
}