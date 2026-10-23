package com.arnix.typey.di

import android.content.Context
import com.arnix.typey.data.remote.ApiService
import com.arnix.typey.data.remote.DataApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing network dependencies and services.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = "http://192.168.100.6:7272/"
    }

    @Provides
    @Singleton
    fun provideDataApiService(@ApplicationContext context: Context): DataApiService {
        return ApiService.create<DataApiService>(context, BASE_URL)
    }

}