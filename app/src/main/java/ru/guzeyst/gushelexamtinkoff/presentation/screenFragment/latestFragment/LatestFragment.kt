package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.latestFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.guzeyst.gushelexamtinkoff.R
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentHotBinding
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentLatestBinding
import ru.guzeyst.gushelexamtinkoff.presentation.adapters.PicturesAdapter
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.hotFragment.HotViewModel
import java.lang.RuntimeException


class LatestFragment : Fragment() {

    private lateinit var viewModel: LatestViewModel
    private var _binding: FragmentLatestBinding? = null
    private val binding: FragmentLatestBinding
        get() = _binding ?: throw RuntimeException("Random fragment binding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LatestViewModel::class.java]
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