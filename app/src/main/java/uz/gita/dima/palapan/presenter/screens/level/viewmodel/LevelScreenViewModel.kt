package uz.gita.dima.palapan.presenter.screens.level.viewmodel

import androidx.lifecycle.MutableLiveData
import uz.gita.dima.palapan.data.LevelEnum

interface LevelScreenViewModel {
    var openGameScreen: MutableLiveData<LevelEnum>

    fun openGameScreen(type: LevelEnum)
}