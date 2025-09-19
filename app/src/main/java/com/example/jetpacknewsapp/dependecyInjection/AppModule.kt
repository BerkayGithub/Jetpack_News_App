package com.example.jetpacknewsapp.dependecyInjection

import android.app.Application
import com.example.jetpacknewsapp.data.manager.LocalUserManagerImpl
import com.example.jetpacknewsapp.domain.manager.LocalUserManager
import com.example.jetpacknewsapp.domain.usecases.AppEntryUseCases
import com.example.jetpacknewsapp.domain.usecases.ReadAppEntry
import com.example.jetpacknewsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        readAppEntry = ReadAppEntry(localUserManager)
    )
}