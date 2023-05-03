package uz.gita.dima.palapan.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.dima.palapan.data.local.database.MyDatabase
import uz.gita.dima.palapan.data.local.database.dao.MyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    companion object {
        private const val DATABASE_NAME = "palapan.db"
    }

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context): MyDatabase {
        return Room.
        databaseBuilder(
            context, MyDatabase::class.java, DATABASE_NAME
        ).createFromAsset("palapan.db").build()
    }

    @Provides
    @Singleton
    fun provideDao(db: MyDatabase): MyDao = db.getMyDao()
}