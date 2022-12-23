package com.daffa.kepinginapajetpack.di

import com.daffa.kepinginapajetpack.data.AppRepository

object Injection {
    fun provideRepository(): AppRepository {
        return AppRepository.getInstance()
    }
}