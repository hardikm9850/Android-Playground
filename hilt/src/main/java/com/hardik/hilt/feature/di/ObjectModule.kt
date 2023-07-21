package com.hardik.hilt.feature.di

import com.hardik.hilt.feature.service.AnalyticsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ObjectModule {
    @Provides
    fun provideAnalyticsService(
        // Potential dependencies of this type
    ): AnalyticsService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(AnalyticsService::class.java)
    }
}