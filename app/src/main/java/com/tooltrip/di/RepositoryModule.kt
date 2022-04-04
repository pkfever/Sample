package com.tooltrip.di

import android.content.Context
import com.tooltrip.domain.repository.InterchangeRepository
import com.tooltrip.domain.repositoryimpl.InterchangeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideInterchangeRepository(@ApplicationContext appContext: Context):
            InterchangeRepository = InterchangeRepositoryImpl(appContext)
}