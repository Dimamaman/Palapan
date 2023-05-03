package uz.gita.dima.palapan.di

import uz.gita.dima.palapan.domain.AppRepository
import uz.gita.dima.palapan.domain.impl.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(): AppRepository = AppRepositoryImpl.getInstance()
}