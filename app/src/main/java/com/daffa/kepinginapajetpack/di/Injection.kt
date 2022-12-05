package com.daffa.kepinginapajetpack.di

import android.content.Context
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.data.source.local.LocalDataSource
import com.daffa.kepinginapajetpack.data.source.local.room.AppDatabase

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val database = AppDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        return AppRepository.getInstance(localDataSource)
    }
}