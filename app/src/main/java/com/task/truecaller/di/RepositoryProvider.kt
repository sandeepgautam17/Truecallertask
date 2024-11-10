package com.task.truecaller.di

import com.task.truecaller.network.BASE_URL
import com.task.truecaller.network.ContentService
import com.task.truecaller.repository.ContentRepository
import com.task.truecaller.repository.ContentRepositoryImpl
import retrofit2.Retrofit

object RepositoryProvider {
    fun provideContentRepository(): ContentRepository {
        val retrofit = provideRetrofit()
        val service = provideContentService(retrofit)
        return ContentRepositoryImpl(service)
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Base URL placeholder
            .build()
    }

    private fun provideContentService(retrofit: Retrofit): ContentService {
        return retrofit.create(ContentService::class.java)
    }
}
