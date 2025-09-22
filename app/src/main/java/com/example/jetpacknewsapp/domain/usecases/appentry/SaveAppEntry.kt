package com.example.jetpacknewsapp.domain.usecases.appentry

import com.example.jetpacknewsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}