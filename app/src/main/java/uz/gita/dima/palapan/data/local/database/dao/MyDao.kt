package uz.gita.dima.palapan.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import uz.gita.dima.palapan.data.local.database.entity.MyData


@Dao
interface MyDao {
    @Query("SELECT * FROM soz")
    suspend fun getAllWords(): List<MyData>
}