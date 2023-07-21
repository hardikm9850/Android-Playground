package com.hardik.hilt.feature.di

import com.hardik.hilt.feature.service.AnalyticsService
import com.hardik.hilt.feature.service.AnalyticsServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AbstractModule {
    /**
     * The @Binds annotation tells Hilt which implementation to use when it needs to provide an instance of an interface.
     *
     * The annotated function provides the following information to Hilt:
     * The function return type tells Hilt what interface the function provides instances of.
     * The function parameter tells Hilt which implementation to provide.
     */
    @Binds
    abstract fun provideAnalyticsService(analyticsServiceImpl: AnalyticsServiceImpl): AnalyticsService

}