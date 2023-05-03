package uz.gita.dima.palapan.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.palapan.domain.DbRepository
import uz.gita.dima.palapan.domain.impl.DbRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DBRepositoryModule {
    @Binds
    fun getDBRepository(impl: DbRepositoryImpl): DbRepository
}