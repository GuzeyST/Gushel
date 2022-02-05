package ru.guzeyst.gushelexamtinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.guzeyst.gushelexamtinkoff.R
import ru.guzeyst.gushelexamtinkoff.databinding.PictureItemBinding
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

class PicturesAdapter :
    ListAdapter<Picture, PicturesAdapter.PictureAdapterViewHolder>(PicturesDiffCallBack) {

    class PictureAdapterViewHolder(
        val binding: PictureItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .fitCenter()
                .into(this.ivGif)
            tvDesc.text = pict.description
        }
    }

}