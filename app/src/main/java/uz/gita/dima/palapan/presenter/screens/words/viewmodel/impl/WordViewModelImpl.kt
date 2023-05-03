package uz.gita.dima.palapan.presenter.screens.words.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.dima.palapan.data.local.database.entity.MyData
import uz.gita.dima.palapan.domain.DbRepository
import uz.gita.dima.palapan.presenter.screens.words.viewmodel.WordViewModel
import javax.inject.Inject

@HiltViewModel
class WordViewModelImpl @Inject constructor(
    private val dbRepository: DbRepository
) : ViewModel(), WordViewModel {
    override val allWords = MutableLiveData<List<MyData>>()

    override  fun getAllWords() {
        viewModelScope.launch {
            allWords.value = dbRepository.getAllWords()
        }
    }
}