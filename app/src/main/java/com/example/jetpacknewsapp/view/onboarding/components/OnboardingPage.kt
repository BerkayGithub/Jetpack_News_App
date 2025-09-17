package com.example.jetpacknewsapp.view.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacknewsapp.R
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.view.Dimensions.IndicatorSize
import com.example.jetpacknewsapp.view.Dimensions.MediumPadding1
import com.example.jetpacknewsapp.view.Dimensions.MediumPadding2
import com.example.jetpacknewsapp.view.onboarding.Page
import com.example.jetpacknewsapp.view.onboarding.pageList

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            page.title,
            modifier = Modifier.padding(top = MediumPadding2).padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.display_small)
        )
        Text(
            page.description,
            modifier = Modifier.padding(bottom = MediumPadding2).padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun onboardingPreview() {
    JetpackNewsAppTheme {
        OnboardingPage(page = pageList[0])
    }
}