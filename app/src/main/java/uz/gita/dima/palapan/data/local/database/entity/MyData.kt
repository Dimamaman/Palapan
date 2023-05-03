package uz.gita.dima.palapan.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "soz")
data class MyData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val qq: String,
    val rus: String,
    val eng: String
)
