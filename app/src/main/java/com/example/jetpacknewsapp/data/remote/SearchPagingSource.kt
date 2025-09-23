package com.example.jetpacknewsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpacknewsapp.domain.model.Article

class SearchPagingSource(
    private val newsAPI: NewsAPI,
    private val sources: String,
    private val searchQuery: String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorposition ->
            val anchorPage = state.closestPageToPosition(anchorposition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsAPI.searchNews(searchQuery = searchQuery, page = page, sources = sources)
            totalNewsCount += newsResponse.body()!!.articles.size
            val articles = newsResponse.body()!!.articles.distinctBy { it.title } // Remove duplicates
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.body()!!.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e : Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}