package uz.gita.dima.palapan.presenter.screens.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymemorygame.data.local.sharedPref.impl.SharedPrefImpl
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.palapan.R
import uz.gita.dima.palapan.data.CardData
import uz.gita.dima.palapan.data.LevelEnum
import uz.gita.dima.palapan.databinding.ScreenGameBinding
import uz.gita.dima.palapan.presenter.screens.game.viewmodel.GameFragmentViewModel
import uz.gita.dima.palapan.presenter.screens.game.viewmodel.impl.GameFragmentViewModelImpl
import uz.gita.dima.palapan.utils.closeAnimation
import uz.gita.dima.palapan.utils.openAnimation
import uz.gita.dima.palapan.utils.remove
import uz.gita.dima.palapan.utils.visible
import java.util.*
import java.util.Locale.*
import javax.inject.Inject

@AndroidEntryPoint
class GameFragment : Fragment(R.layout.screen_game) {
    private val binding: ScreenGameBinding by viewBinding(ScreenGameBinding::bind)
    private var defLevel = LevelEnum.EASY
    private val args by navArgs<GameFragmentArgs>()
    private var _height = 0
    private var _width = 0

    private val images = ArrayList<ImageView>()
    private var steps = 0
    private var isAnimated = false
    private var firstPosition = -1
    private var secondPosition = -1

    private var imageCount = 0
    private var levelNumber = 1

    private val viewModel: GameFragmentViewModel by viewModels<GameFragmentViewModelImpl>()
    private lateinit var handler: Handler
    @Inject lateinit var shared: SharedPrefImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goWordsScreen.observe(this,goWordScreen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        defLevel = args.level
        handler = Handler(Looper.getMainLooper())

        viewModel.restart.observe(viewLifecycleOwner) {
            restart()
        }

        viewModel.goMenu.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

//        viewModel.goWordsScreen.observe(viewLifecycleOwner) {
//            findNavController().navigate(R.id.action_gameScreen_to_wordsScreen)
//        }

        binding.nextLevel.setOnClickListener {
            binding.apply {
                menu.isEnabled = true
                reload.isEnabled = true
            }
            levelNumber++
            takeLevelByMenu(levelNumber)
            if (levelNumber < 11) {
                binding.levelText.text = levelNumber.toString()
                restart()
            } else {
                takeLevelByMenu(1)
                findNavController().popBackStack()
            }
        }
        val menuName = defLevel.name.lowercase(ROOT)
        binding.textMenu.text =
            menuName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(ROOT) else it.toString() }
        loadSize()

//        viewModel.menu.observe(viewLifecycleOwner, menuObserver)

        /*lifecycleScope.launch {
            viewModel.putIntStep(77)
        }

        lifecycleScope.launch {
            val step = viewModel.getIntStep("step")
            Log.d("DDD","Step -> $step")
            binding.attempt.text = step.toString()
        }*/

        binding.menu.setOnClickListener {
            viewModel.goWordsScreen()
        }

        binding.home.setOnClickListener {
            viewModel.goMenu()
        }

        binding.reload.setOnClickListener {
            viewModel.restart()
        }
    }

    private val goWordScreen = Observer<Unit> {
        findNavController().navigate(R.id.action_gameScreen_to_wordsScreen)
    }

    private fun loadSize() {
        binding.space.post {
            _height = binding.container.height / defLevel.verCount
            _width = binding.container.width / defLevel.horCount
            val count = defLevel.horCount * defLevel.verCount
            imageCount = count
            viewModel.getData(count)
            describeCardData()
        }
        viewModel.imageLiveData.observe(viewLifecycleOwner, imageAddClickObserver)
    }

    private fun restart() {
        binding.container.removeAllViews()
//        imageList.clear()
//        attemptCount = 1
//        vb.attempt.text = attemptCount.toString()
        firstPosition = -1
        images.clear()
        steps = 1
        secondPosition = -1
        isAnimated = false
        loadSize()
        binding.nextLevel.visibility = View.GONE
    }

    private fun takeLevelByMenu(level: Int) {
        shared.isNew = false
        when (shared.menu) {

            1 -> {
                shared.easy = level
            }
            2 -> {
                shared.medium = level
            }
            3 -> {
                shared.hard = level
            }
        }

        /*viewModel.putBooleanIsNew(false)
        lifecycleScope.launch {
            when(viewModel.getIntMenu("menu")) {
                1 -> {
                    viewModel.putIntEasy(level)
                }
                2 -> {
                    viewModel.putIntMedium(level)
                }
                3 -> {
                    viewModel.putIntHard(level)
                }
            }
        }*/
    }

    private val imageAddClickObserver = Observer<List<CardData>> {
        for (i in images.indices) {
            images[i].apply {
                val data = it[i]
                tag = data
                setOnClickListener {
                    steps++
//                    shared.step = steps
                    if (isAnimated) {
                        return@setOnClickListener
                    }
                    if (firstPosition == -1) {
                        firstPosition = i
                        if (rotationY == 0f) {
                            this.openAnimation { }
                        }
                    } else if (firstPosition != i) {
                        isAnimated = true
                        secondPosition = i
                        if (rotationY == 0f) {
                            this.openAnimation {
                                check()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun check() {
        val firstImage = images[firstPosition].tag as CardData
        val secondImage = images[secondPosition].tag as CardData


        if (firstImage.id == secondImage.id) {
            handler.postDelayed({
                images[firstPosition].remove {
                    scaleX = 1f
                    scaleY = 1f
                }
                images[secondPosition].remove {
                    scaleX = 1f
                    scaleY = 1f
                    imageCount -= 2

                    if (imageCount == 0) {
                        finishGame()
                    }
                    firstPosition = -1
                    secondPosition = -1
                    isAnimated = false
                }

            }, 250)
        } else {
            handler.postDelayed({
                images[firstPosition].closeAnimation()
                images[secondPosition].closeAnimation {
                    firstPosition = -1
                    secondPosition = -1
                    isAnimated = false
                }
            }, 250)
        }

    }

    private fun describeCardData() {
        for (i in 0 until defLevel.horCount) {
            for (j in 0 until defLevel.verCount) {
                val image = ImageView(requireContext())
                binding.container.addView(image)
                val lp = image.layoutParams as ConstraintLayout.LayoutParams
                lp.apply {
                    width = _width
                    height = _height
                }
                lp.setMargins(4, 4, 4, 4)
                image.layoutParams = lp
                image.elevation = 5f
                image.setImageResource(R.drawable.nagis1icon)
//                image.scaleType = ImageView.ScaleType.CENTER_CROP
                image.animate()
                    .x(i * _width * 1f)
                    .y(j * _height * 1f)
                    .setDuration(1000)
                    .start()
                images.add(image)
            }
        }
        loadDataByMenu()
    }

    private fun finishGame() {
        for (image in images) {
            image.visible()
            image.isEnabled = false
        }
        binding.apply {
            menu.isEnabled = false
            reload.isEnabled = false
        }

        if (levelNumber == 10) {
            binding.congrats.visibility = View.VISIBLE
            binding.home.isEnabled = true
            takeLevelByMenu(0)
        } else binding.nextLevel.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataByMenu() {

        if (shared.menu == 1) {
            if (!shared.isNew) {
                binding.levelText.text = "${shared.easy} / 10"
                levelNumber = shared.easy
            } else {
                binding.levelText.text = "$levelNumber / 10"
                shared.easy = 1

            }
        } else if (shared.menu == 2) {
            if (!shared.isNew) {
                binding.levelText.text = "${shared.medium} / 10"
                levelNumber = shared.medium
            } else {
                binding.levelText.text = "$levelNumber / 10"
                shared.medium = 1

            }
        } else {
            if (!shared.isNew) {
                binding.levelText.text = "${shared.hard} / 10"
                levelNumber = shared.hard
            } else {
                binding.levelText.text = "$levelNumber / 10"
                shared.hard = 1
            }
        }

        /*lifecycleScope.launch(Dispatchers.Main) {
            if (viewModel.getIntMenu("menu") == 1) {
                if ((!viewModel.getBooleanIsNew("isNew")!!)) {
                    binding.levelText.text = "${viewModel.getIntEasy("easy")}/10"
                    levelNumber = viewModel.getIntEasy("easy")!!
                } else {
                    binding.levelText.text = "$levelNumber/10"
                    viewModel.putIntEasy(1)
                }
            } else if (viewModel.getIntMenu("menu") == 2){
                if ((!viewModel.getBooleanIsNew("isNew")!!)) {
                    binding.levelText.text = "${viewModel.getIntMedium("medium")}/10"
                    levelNumber = viewModel.getIntMedium("medium")!!
                } else {
                    binding.levelText.text = "$levelNumber/10"
                    viewModel.putIntMedium(1)
                }
            } else {
                if ((!viewModel.getBooleanIsNew("isNew")!!)) {
                    binding.levelText.text = "${viewModel.getIntHard("hard")}/10"
                    levelNumber = viewModel.getIntHard("hard")!!
                } else {
                    binding.levelText.text = "$levelNumber/10"
                    viewModel.putIntHard(1)
                }
            }
        }*/
    }

    /*@SuppressLint("SetTextI18n")
    private val menuObserver = Observer<Int> {
        if (it == 1) {
            viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
                if (!isNew) {
                    viewModel.easy.observe(viewLifecycleOwner) { easy ->
                        binding.levelText.text = "${easy}/10"
                        levelNumber = easy

                    }
                } else {
                    binding.levelText.text = "$levelNumber/10"
                    viewModel.putIntEasy(1)
                }
            }
        } else if (it == 2) {
            viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
                if (!isNew) {
                    viewModel.medium.observe(viewLifecycleOwner) { medium ->
                        binding.levelText.text = "${medium}/10"
                        levelNumber = medium

                    }
                } else {
                    binding.levelText.text = "$levelNumber/10"
                    viewModel.putIntMedium(1)
                }
            }
        } else {
            viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
                if (!isNew) {
                    viewModel.hard.observe(viewLifecycleOwner) { hard ->
                        binding.levelText.text = "${hard}/10"
                        levelNumber = hard

                    }
                } else {
                    binding.levelText.text = "$levelNumber/10"
                    viewModel.putIntHard(1)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private val easyObserver = Observer<Int> {
        binding.levelText.text = "${it}/10"
        levelNumber = it
    }

    @SuppressLint("SetTextI18n")
    private val mediumObserver = Observer<Int> {
        binding.levelText.text = "${it}/10"
        levelNumber = it
    }

    @SuppressLint("SetTextI18n")
    private val hardObserver = Observer<Int> {
        binding.levelText.text = "${it}/10"
        levelNumber = it
    }

    @SuppressLint("SetTextI18n")
    private val isNewObserver = Observer<Boolean> { isNew ->
        if (!isNew) {
            viewModel.easy.observe(viewLifecycleOwner) {
                binding.levelText.text = "${it}/10"
            }
            levelNumber = shared.easy
        } else {
            binding.levelText.text = "$levelNumber/10"
            viewModel.putIntEasy(1)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataByMenu() {
        viewModel.menu.observe(viewLifecycleOwner) { menu ->
            if (menu == 1) {
                viewModel.isNew.observe(viewLifecycleOwner) { isNew ->
                    if (!isNew) {
                        viewModel.easy.observe(viewLifecycleOwner) {
                            binding.levelText.text = "$it/10"
                        }
                    } else {
                        binding.levelText.text = "$levelNumber/10"
                        viewModel.putIntEasy(1)
                    }
                }
            } else if (menu == 2) {

            } else {

            }
        }
        if (shared.menu == 1) {
            if (!shared.isNew) {
                vb.levelText.text = "${shared.easy}/10"
                levelNumber = shared.easy
            } else {
                vb.levelText.text = "$levelNumber/10"
                shared.easy = 1
            }
        } else if (shared.menu == 2) {
            if (!shared.isNew) {
                vb.levelText.text = "${shared.medium}/10"
                levelNumber = shared.medium
            } else {
                vb.levelText.text = "$levelNumber/10"
                shared.medium = 1

            }
        } else {
            if (!shared.isNew) {
                vb.levelText.text = "${shared.hard}/10"
                levelNumber = shared.hard
            } else {
                vb.levelText.text = "$levelNumber/10"
                shared.hard = 1
            }
        }
    }*/
}