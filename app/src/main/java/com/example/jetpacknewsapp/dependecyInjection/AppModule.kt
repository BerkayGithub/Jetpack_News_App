package com.example.jetpacknewsapp.dependecyInjection

import android.app.Application
import com.example.jetpacknewsapp.Constants.baseUrl
import com.example.jetpacknewsapp.data.manager.LocalUserManagerImpl
import com.example.jetpacknewsapp.data.remote.NewsAPI
import com.example.jetpacknewsapp.data.remote.NewsRepositoryImpl
import com.example.jetpacknewsapp.domain.manager.LocalUserManager
import com.example.jetpacknewsapp.domain.repository.NewsRepository
import com.example.jetpacknewsapp.domain.usecases.appentry.AppEntryUseCases
import com.example.jetpacknewsapp.domain.usecases.appentry.ReadAppEntry
import com.example.jetpacknewsapp.domain.usecases.appentry.SaveAppEntry
import com.example.jetpacknewsapp.domain.usecases.news.GetNews
import com.example.jetpacknewsapp.domain.usecases.news.NewsUseCases
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
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsAPI() : NewsAPI {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPI: NewsAPI
    ) : NewsRepository = NewsRepositoryImpl(newsAPI)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) = NewsUseCases(
        getNews = GetNews(newsRepository)
    )
}