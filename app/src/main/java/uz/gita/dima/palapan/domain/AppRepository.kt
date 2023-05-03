package uz.gita.dima.palapan.domain
import uz.gita.dima.palapan.data.CardData

interface AppRepository {
    fun getData(count: Int) : kotlinx.coroutines.flow.Flow<List<CardData>>
}