package ru.guzeyst.gushelexamtinkoff.presentation.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import ru.guzeyst.gushelexamtinkoff.R
import ru.guzeyst.gushelexamtinkoff.databinding.PictureItemBinding
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

class PicturesAdapter :
    ListAdapter<Picture, PicturesAdapter.PictureAdapterViewHolder>(PicturesDiffCallBack) {

    var positionAdapterListener: ((Unit) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureAdapterViewHolder {
        val binding = PictureItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PictureAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureAdapterViewHolder, position: Int) {
        with(holder.binding) {
            val pict = getItem(position)
            Glide.with(this.root)
                .load(pict.gifURL)
                .error(R.drawable.ic_baseline_error_24)
                .placeholder(R.drawable.spiner)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .centerCrop()
                .into(this.ivGif)
            tvDesc.text = pict.description

            if(position > itemCount - 2){
                positionAdapterListener?.invoke(Unit)
            }
        }
    }

    class PictureAdapterViewHolder(
        val binding: PictureItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
