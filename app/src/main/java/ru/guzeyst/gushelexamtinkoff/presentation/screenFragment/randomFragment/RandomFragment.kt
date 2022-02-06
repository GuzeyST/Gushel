package ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.randomFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.guzeyst.gushelexamtinkoff.databinding.FragmentRandomBinding
import ru.guzeyst.gushelexamtinkoff.presentation.PictureApp
import ru.guzeyst.gushelexamtinkoff.presentation.screenFragment.ViewModelFacroty
import javax.inject.Inject


class RandomFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFacroty

    private lateinit var viewModel: RandomViewModel
    private var _binding: FragmentRandomBinding? = null
    private val binding: FragmentRandomBinding
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
    ): View {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RandomViewModel::class.java]
        setObserveViewModel()
        setClickListener()
    }

    private fun setClickListener() {
        binding.ibNext.setOnClickListener { viewModel.getNextImage() }
        binding.ibBack.setOnClickListener { viewModel.getPreviousPicture() }
    }

    private fun setObserveViewModel() {
        viewModel.currentPictures.observe(viewLifecycleOwner) {
            val progressBar = getCircularProgressBar(requireContext())
            Glide.with(this)
                .load(it.gifURL)
                .error(progressBar)
                .placeholder(progressBar)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerInside()
                .into(binding.imageView)
            binding.tvDesc.text = it.description
        }

        viewModel.isLastPicture.observe(viewLifecycleOwner) {
            binding.ibBack.visibility = when (it) {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }

        viewModel.isLoading.observe(this) {
            val isLoading = it
            binding.ibNext.isClickable = !isLoading
            binding.ibBack.isClickable = !isLoading
        }

        viewModel.isOnline.observe(this) {
            binding.tvIsNotOnline.visibility  = when (it) {
                true -> View.GONE
                else -> View.VISIBLE
            }
        }
    }

    private fun getCircularProgressBar(context: Context): CircularProgressDrawable {
        val circularProgress = CircularProgressDrawable(context)
        circularProgress.strokeWidth = 10f
        circularProgress.centerRadius = 45f
        circularProgress.start()
        return circularProgress
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}