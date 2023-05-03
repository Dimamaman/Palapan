package uz.gita.dima.palapan.presenter.screens.words.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.dima.palapan.data.local.database.entity.MyData

interface WordViewModel {
    val allWords: LiveData<List<MyData>>
    fun getAllWords()
}