package com.arnix.typey.di

import com.arnix.typey.repository.autotext.AutoTextRepository
import com.arnix.typey.repository.autotext.AutoTextRepositoryImpl
import com.arnix.typey.repository.clipboard.ClipboardRepository
import com.arnix.typey.repository.clipboard.ClipboardRepositoryImpl
import com.arnix.typey.repository.data.DataApiRepository
import com.arnix.typey.repository.data.DataApiRepositoryImpl
import com.arnix.typey.repository.productremote.ProductRemoteRepository
import com.arnix.typey.repository.productremote.ProductRemoteRepositoryImpl
import com.arnix.typey.repository.templatetext.TemplateTextRepository
import com.arnix.typey.repository.templatetext.TemplateTextRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module binding repository implementations to their interfaces.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getAutoTextRepository(repository: AutoTextRepositoryImpl): AutoTextRepository

    @Binds
    @Singleton
    abstract fun bindDataApiRepository(repository: DataApiRepositoryImpl): DataApiRepository

    @Binds
    @Singleton
    abstract fun bindProductRemoteRepository(repository: ProductRemoteRepositoryImpl): ProductRemoteRepository

    @Binds
    @Singleton
    abstract fun bindClipboardRepository(repository: ClipboardRepositoryImpl): ClipboardRepository

    @Binds
    @Singleton
    abstract fun bindTemplateTextRepository(repository: TemplateTextRepositoryImpl): TemplateTextRepository

}