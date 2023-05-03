package uz.gita.dima.palapan.presenter.screens.level

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymemorygame.data.local.sharedPref.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.palapan.R
import uz.gita.dima.palapan.data.LevelEnum
import uz.gita.dima.palapan.databinding.ScreenLevelBinding
import uz.gita.dima.palapan.presenter.screens.level.viewmodel.LevelScreenViewModel
import uz.gita.dima.palapan.presenter.screens.level.viewmodel.impl.LevelScreenViewModelImpl
import javax.inject.Inject

@AndroidEntryPoint
class LevelScreen : Fragment(R.layout.screen_level) {
    private val binding : ScreenLevelBinding by viewBinding(ScreenLevelBinding::bind)
    private val viewModel: LevelScreenViewModel by viewModels<LevelScreenViewModelImpl>()
    @Inject lateinit var shared: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openGameScreen.observe(this,openGameScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*binding.easy.setOnClickListener {
            viewModelGame.putIntMenu(1)
            lifecycleScope.launch {
                if (viewModelGame.getIntEasy("easy")!! < 2) {
                    viewModelGame.putBooleanIsNew(true)
                }
            }

            lifecycleScope.launch(Dispatchers.Main) {
                Log.d("DDD","isNew -> ${viewModelGame.getBooleanIsNew("isNew")}")
                Log.d("DDD","isNew -> ${viewModelGame.getIntMenu("menu")}")
                if (!viewModelGame.getBooleanIsNew("isNew")!! && viewModelGame.getIntMenu("menu") == 1) {

                } else {
                    viewModelGame.putIntMenu(1)
                    levelChoose(LevelEnum.EASY)
                }
            }
        }
        binding.medium.setOnClickListener {
            viewModelGame.putIntMenu(2)
            viewModel.openGameScreen(LevelEnum.MEDIUM)
            viewModelGame.putIntMenu(2)
            lifecycleScope.launch {
                if (viewModelGame.getIntMedium("medium")!! < 2) {
                    viewModelGame.putBooleanIsNew(true)
                }
            }

            lifecycleScope.launch {
                if (!viewModelGame.getBooleanIsNew("isNew")!! && viewModelGame.getIntMenu("menu") == 2) {

                } else {
                    viewModelGame.putIntMenu(2)
                    levelChoose(LevelEnum.MEDIUM)
                }
            }
        }
        binding.hard.setOnClickListener {
            viewModelGame.putIntMenu(3)
            viewModel.openGameScreen(LevelEnum.HARD)
            viewModelGame.putIntMenu(3)
            lifecycleScope.launch {
                if (viewModelGame.getIntHard("hard")!! < 2) {
                    viewModelGame.putBooleanIsNew(true)
                }
            }

            lifecycleScope.launch {
                if (!viewModelGame.getBooleanIsNew("isNew")!! && viewModelGame.getIntMenu("menu") == 3) {

                } else {
                    viewModelGame.putIntMenu(3)
                    levelChoose(LevelEnum.HARD)
                }
            }
        }*/

        binding.easy.setOnClickListener {
            shared.menu = 1
            shared.isNew = shared.easy < 2

            if (!shared.isNew && shared.menu == 1) {
                showDialog(LevelEnum.EASY)
            } else {
                shared.menu = 1
                levelChoose(LevelEnum.EASY)
            }
        }

        binding.medium.setOnClickListener {
            shared.menu = 2
            shared.isNew = shared.medium < 2
            if (!shared.isNew && shared.menu == 2) {
                showDialog(LevelEnum.MEDIUM)
            } else {
                shared.menu = 2
                levelChoose(LevelEnum.MEDIUM)
            }
        }

        binding.hard.setOnClickListener {
            shared.menu = 3
            shared.isNew = shared.hard < 2
            if (!shared.isNew && shared.menu == 3) {
                showDialog(LevelEnum.HARD)
            } else {
                levelChoose(LevelEnum.HARD)
            }
        }
    }

    private val openGameScreenObserver = Observer<LevelEnum> {
        findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(it))
    }

    private fun levelChoose(level: LevelEnum) {
        findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(level))
    }

    private fun openGameScreen(level: LevelEnum) {
        findNavController().navigate(LevelScreenDirections.actionLevelScreenToGameScreen(level))
    }

    private fun showDialog(level: LevelEnum) {
        val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.dialog_menu)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnContinue = dialog.findViewById<TextView>(R.id.btn_continue)
        val btnNewGame = dialog.findViewById<TextView>(R.id.btn_new_game)

        btnContinue.setOnClickListener {

            levelChoose(level)
            dialog.dismiss()
        }

        btnNewGame.setOnClickListener {

            shared.isNew = true
            levelChoose(level)
            dialog.dismiss()
        }

        dialog.show()
    }
}
