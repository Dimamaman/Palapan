package uz.gita.dima.palapan.presenter.screens.level.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.dima.palapan.data.LevelEnum
import uz.gita.dima.palapan.presenter.screens.level.viewmodel.LevelScreenViewModel

class LevelScreenViewModelImpl: ViewModel(), LevelScreenViewModel {
    override var openGameScreen = MutableLiveData<LevelEnum>()

    override fun openGameScreen(type: LevelEnum) {
        openGameScreen.value = type
    }
}