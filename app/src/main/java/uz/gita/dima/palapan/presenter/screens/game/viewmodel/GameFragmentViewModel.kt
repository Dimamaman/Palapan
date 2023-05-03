package uz.gita.dima.palapan.presenter.screens.game.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.dima.palapan.data.CardData

interface GameFragmentViewModel {
    val imageLiveData: LiveData<List<CardData>>
    val restart: LiveData<Unit>
    val goMenu: LiveData<Unit>
    val goWordsScreen: LiveData<Unit>

    fun getData(count: Int)

    // Data Store
    fun putIntStep(steps: Int)
    suspend fun getIntStep(key: String): Int

    fun putIntMenu(menu: Int)
    suspend fun getIntMenu(key: String): Int

    fun putIntEasy(easy: Int)
    suspend fun getIntEasy(key: String): Int

    fun putIntMedium(medium: Int)
    suspend fun getIntMedium(key: String): Int

    fun putIntHard(hard: Int)
    suspend fun getIntHard(key: String): Int

    fun putBooleanIsNew(isNew: Boolean)
    suspend fun getBooleanIsNew(key: String): Boolean
    fun restart()
    fun goMenu()
    fun goWordsScreen()
}