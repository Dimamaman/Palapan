package uz.gita.dima.palapan.domain

import uz.gita.dima.palapan.data.local.database.entity.MyData

interface DbRepository {
    suspend fun getAllWords(): List<MyData>
}