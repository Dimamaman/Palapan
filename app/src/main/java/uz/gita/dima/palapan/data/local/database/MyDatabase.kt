package uz.gita.dima.palapan.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.dima.palapan.data.local.database.dao.MyDao
import uz.gita.dima.palapan.data.local.database.entity.MyData

@Database(entities = [MyData::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getMyDao(): MyDao
}