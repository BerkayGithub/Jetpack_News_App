package com.example.jetpacknewsapp.dependecyInjection

import android.app.Application
import androidx.room.Room
import com.example.jetpacknewsapp.Constants.baseUrl
import com.example.jetpacknewsapp.Constants.databaseName
import com.example.jetpacknewsapp.data.local.NewsDAO
import com.example.jetpacknewsapp.data.local.NewsDatabase
import com.example.jetpacknewsapp.data.local.NewsTypeConverter
import com.example.jetpacknewsapp.data.manager.LocalUserManagerImpl
import com.example.jetpacknewsapp.data.remote.NewsAPI
import com.example.jetpacknewsapp.data.remote.NewsRepositoryImpl
import com.example.jetpacknewsapp.domain.manager.LocalUserManager
import com.example.jetpacknewsapp.domain.repository.NewsRepository
import com.example.jetpacknewsapp.domain.usecases.appentry.AppEntryUseCases
import com.example.jetpacknewsapp.domain.usecases.appentry.ReadAppEntry
import com.example.jetpacknewsapp.domain.usecases.appentry.SaveAppEntry
import com.example.jetpacknewsapp.domain.usecases.news.DeleteArticle
import com.example.jetpacknewsapp.domain.usecases.news.GetNews
import com.example.jetpacknewsapp.domain.usecases.news.NewsUseCases
import com.example.jetpacknewsapp.domain.usecases.news.SearchNews
import com.example.jetpacknewsapp.domain.usecases.news.SelectArticles
import com.example.jetpacknewsapp.domain.usecases.news.UpsertArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application,
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager,
    ) = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsAPI(): NewsAPI {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPI: NewsAPI,
    ): NewsRepository = NewsRepositoryImpl(newsAPI)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDAO: NewsDAO
    ) = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        upsertArticle = UpsertArticle(newsDAO),
        deleteArticle = DeleteArticle(newsDAO),
        selectArticles = SelectArticles(newsDAO),
    )

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = databaseName
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDAO(
        newsDatabase: NewsDatabase
    ): NewsDAO = newsDatabase.newsDAO

}