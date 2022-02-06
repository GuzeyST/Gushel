package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.topFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.guzeyst.gushelexamtinkoff.R
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentLatestBinding
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentTopBinding
import ru.guzeyst.gushelexamtinkoff.presentation.PictureApp
import ru.guzeyst.gushelexamtinkoff.presentation.adapters.PicturesAdapter
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.ViewModelFacroty
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.latestFragment.LatestViewModel
import java.lang.RuntimeException
import javax.inject.Inject

class TopFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFacroty
    private lateinit var viewModel: TopViewModel
    private var _binding: FragmentTopBinding? = null
    private val binding: FragmentTopBinding
        get() = _binding ?: throw RuntimeException("Random fragment binding is null")

    private val component by lazy {
        (requireActivity().application as PictureApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[TopViewModel::class.java]
        val adapter = PicturesAdapter()
        adapter.positionAdapterListener = {
            viewModel.loadNextPage()
        }
        binding.rvPictures.adapter = adapter
        viewModel.listPictures.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}