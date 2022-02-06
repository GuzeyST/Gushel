package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentRandomBinding
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
        setObserveViewModel()
        setClickListener()
    }

    private fun setClickListener(){
        binding.ibNext.setOnClickListener{viewModel.getNextImage()}
        binding.ibBack.setOnClickListener{viewModel.getPreviousPivture()}
    }

    private fun setObserveViewModel(){
        viewModel.currentPictures.observe(viewLifecycleOwner){
            Glide.with(this)
                .load(it.gifURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageView)
            binding.tvDesc.text = it.description
        }

        viewModel.isLastPicture.observe(viewLifecycleOwner){
            binding.ibBack.visibility = when(it) {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }

        viewModel.isLoading.observe(this){
            val isLoading = it
            binding.ibNext.isClickable = !isLoading
            binding.ibBack.isClickable = !isLoading
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}