package uz.gita.dima.palapan.presenter.screens.words

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.dima.palapan.presenter.screens.words.viewmodel.impl.WordViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dima.palapan.R
import uz.gita.dima.palapan.data.local.database.entity.MyData
import uz.gita.dima.palapan.databinding.ScreenWordBinding
import uz.gita.dima.palapan.presenter.screens.adapter.MyAdapter
import uz.gita.dima.palapan.presenter.screens.words.viewmodel.WordViewModel
import javax.inject.Inject


@AndroidEntryPoint
class WordsScreen : Fragment(R.layout.screen_word) {

    @Inject
    lateinit var myAdapter: MyAdapter
    private val binding by viewBinding(ScreenWordBinding::bind)
    private val viewModel: WordViewModel by viewModels<WordViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllWords()
        viewModel.allWords.observe(viewLifecycleOwner,allWordObserver)
        binding.apply {
            rv.setHasFixedSize(false)
            rv.adapter = myAdapter
        }

//        requireActivity().onBackPressedDispatcher.onBackPressed()
    }


    private val allWordObserver = Observer<List<MyData>> {
        Log.d("TTT","All List -> $it")
        myAdapter.submitList(it)
    }
}