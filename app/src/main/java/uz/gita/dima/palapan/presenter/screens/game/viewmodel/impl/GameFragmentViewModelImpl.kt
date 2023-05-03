package uz.gita.dima.palapan.presenter.screens.game.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uz.gita.dima.palapan.data.local.datastore.DataStore
import uz.gita.dima.palapan.data.local.datastore.impl.DataStoreImpl
import uz.gita.dima.palapan.presenter.screens.game.viewmodel.GameFragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.dima.palapan.data.CardData
import uz.gita.dima.palapan.domain.AppRepository
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), GameFragmentViewModel {
    //    private val repository = AppRepository.getInstance()
    private val dataStore: DataStore = DataStoreImpl.getInstance()

    override val imageLiveData = MutableLiveData<List<CardData>>()
    override val restart = MutableLiveData<Unit>()
    override val goMenu = MutableLiveData<Unit>()
    override val goWordsScreen = MutableLiveData<Unit>()

    override fun getData(count: Int) {
        viewModelScope.launch {
            repository.getData(count).collect {
                imageLiveData.postValue(it)
            }
        }
    }

    override fun putIntStep(steps: Int) {
        viewModelScope.launch {
            dataStore.putIntStep("step", value = steps)
        }
    }

    override suspend fun getIntStep(key: String): Int {
        return dataStore.getIntStep(key) ?: 1
    }

    override fun putIntMenu(menu: Int) {
        viewModelScope.launch {
            dataStore.putIntMenu("menu", menu)
        }
    }

    override suspend fun getIntMenu(key: String): Int {
        return dataStore.getIntMenu(key) ?: 0
    }

    override fun putIntEasy(easy: Int) {
        viewModelScope.launch {
            dataStore.putIntEasy("easy", easy)
        }
    }

    override suspend fun getIntEasy(key: String): Int {
        return dataStore.getIntEasy(key) ?: 0
    }

    override fun putIntMedium(medium: Int) {
        viewModelScope.launch {
            dataStore.putIntMedium("medium", medium)
        }
    }

    override suspend fun getIntMedium(key: String): Int {
        return dataStore.getIntMedium(key) ?: 0
    }

    override fun putIntHard(hard: Int) {
        viewModelScope.launch {
            dataStore.putIntHard("hard", hard)
        }
    }

    override suspend fun getIntHard(key: String): Int {
        return dataStore.getIntHard(key) ?: 0
    }

    override fun putBooleanIsNew(isNew: Boolean) {
        viewModelScope.launch {
            dataStore.putBooleanIsNew("isNew", isNew)
        }
    }

    override suspend fun getBooleanIsNew(key: String): Boolean {
        return dataStore.getBooleanIsNew(key) ?: false
    }

    override fun restart() {
        restart.value = Unit
    }

    override fun goMenu() {
        goMenu.value = Unit
    }

    override fun goWordsScreen() {
        goWordsScreen.value = Unit
    }
}