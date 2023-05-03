package uz.gita.dima.palapan.di

import android.content.Context
import android.content.SharedPreferences
import com.example.mymemorygame.data.local.sharedPref.SharedPref
import com.example.mymemorygame.data.local.sharedPref.impl.SharedPrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule() {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MemoryGame", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideMySharedPref(sh: SharedPreferences): SharedPref = SharedPrefImpl(sh)
}