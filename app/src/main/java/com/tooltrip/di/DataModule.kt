package com.tooltrip.di

import android.content.Context
import com.tooltrip.ToolTripApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ToolTripApplication {
        return app as ToolTripApplication
    }
}