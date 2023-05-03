package uz.gita.dima.palapan.domain.impl

import kotlinx.coroutines.flow.flow
import uz.gita.dima.palapan.R
import uz.gita.dima.palapan.data.CardData
import uz.gita.dima.palapan.domain.AppRepository

class AppRepositoryImpl: AppRepository {

    companion object {
        private lateinit var instance: AppRepository

        fun getInstance(): AppRepository {
            if ((!(Companion::instance.isInitialized))) {
                instance = AppRepositoryImpl()
            }
            return instance
        }
    }

    private val cardList = ArrayList<CardData>()

    init {
        loadData()
    }

    private fun loadData() {
        cardList.add(CardData(R.drawable.shiver, 1))
        cardList.add(CardData(R.drawable.hear, 2))
        cardList.add(CardData(R.drawable.celebrate, 3))
        cardList.add(CardData(R.drawable.look, 4))
        cardList.add(CardData(R.drawable.miss, 5))
        cardList.add(CardData(R.drawable.like, 6))
        cardList.add(CardData(R.drawable.answer, 7))
        cardList.add(CardData(R.drawable.welcome, 8))
        cardList.add(CardData(R.drawable.love, 9))
        cardList.add(CardData(R.drawable.walk, 10))
        cardList.add(CardData(R.drawable.laugh, 11))
        cardList.add(CardData(R.drawable.worry, 12))
        cardList.add(CardData(R.drawable.write, 13))
        cardList.add(CardData(R.drawable.shout, 14))
        cardList.add(CardData(R.drawable.lie, 15))
        cardList.add(CardData(R.drawable.think, 16))
        cardList.add(CardData(R.drawable.serve, 17))
        cardList.add(CardData(R.drawable.recycle, 18))
        cardList.add(CardData(R.drawable.work, 19))
        cardList.add(CardData(R.drawable.sweep, 20))
        cardList.add(CardData(R.drawable.carry, 21))
        cardList.add(CardData(R.drawable.hold, 22))
        cardList.add(CardData(R.drawable.eat, 23))
        cardList.add(CardData(R.drawable.cook, 24))
        cardList.add(CardData(R.drawable.catch_, 25))
        cardList.add(CardData(R.drawable.throw_, 26))
        cardList.add(CardData(R.drawable.yawn, 27))
        cardList.add(CardData(R.drawable.wear, 28))
        cardList.add(CardData(R.drawable.bounce, 29))
        cardList.add(CardData(R.drawable.cry, 30))
        cardList.add(CardData(R.drawable.wake_up, 31))
        cardList.add(CardData(R.drawable.sleep, 32))
        cardList.add(CardData(R.drawable.listen, 33))
        cardList.add(CardData(R.drawable.dance, 34))
        cardList.add(CardData(R.drawable.wash, 35))
        cardList.add(CardData(R.drawable.play, 36))
        cardList.add(CardData(R.drawable.sing, 37))
        cardList.add(CardData(R.drawable.run, 38))
        cardList.add(CardData(R.drawable.study, 39))
        cardList.add(CardData(R.drawable.paint, 40))
    }

    override fun getData(count: Int) : kotlinx.coroutines.flow.Flow<List<CardData>> = flow {
        cardList.shuffle()
        val ls = cardList.subList(0,count / 2)
        val result = ArrayList<CardData>(count)
        result.addAll(ls)
        result.addAll(ls)
        result.shuffle()
        emit(result)
    }
}