package uz.gita.dima.palapan.domain.impl

import uz.gita.dima.palapan.data.local.database.dao.MyDao
import uz.gita.dima.palapan.data.local.database.entity.MyData
import uz.gita.dima.palapan.domain.DbRepository
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(
    private val dao: MyDao
): DbRepository {
    override suspend fun getAllWords(): List<MyData> {
        return dao.getAllWords()
    }
}