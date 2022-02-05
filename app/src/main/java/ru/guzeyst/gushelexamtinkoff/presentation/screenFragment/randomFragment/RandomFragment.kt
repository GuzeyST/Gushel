package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentRandomBinding
import ru.guzeyst.gushelexamtinkoff.presentation.adapters.PicturesAdapter
import java.lang.RuntimeException


class RandomFragment : Fragment() {

    private lateinit var viewModel: RandomViewModel
    private var _binding: FragmentRandomBinding? = null
    private val binding: FragmentRandomBinding
        get() = _binding ?: throw RuntimeException("Random fragment binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RandomViewModel::class.java]
        val adapter = PicturesAdapter()
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