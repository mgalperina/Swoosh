package com.mgalperina.swoosh

import com.mgalperina.swoosh.services.ApiService
import com.mgalperina.swoosh.services.SimpleApiService
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideApiService(): ApiService {
        return SimpleApiService()
    }
}