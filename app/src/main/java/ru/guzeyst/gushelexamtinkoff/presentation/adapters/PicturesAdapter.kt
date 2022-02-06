package ru.guzeyst.gushelexamtinkoff.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

            val progressBar = getCircularProgressBar(holder.itemView.context)

            Glide.with(this.root)
                .load(pict.gifURL)
                .error(progressBar)
                .placeholder(progressBar)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerInside()
                .into(this.ivGif)
            tvDesc.text = pict.description

            if(position > itemCount - 2){
                positionAdapterListener?.invoke(Unit)
            }
        }
    }

    private fun getCircularProgressBar(context: Context): CircularProgressDrawable{
        val circularProgress = CircularProgressDrawable(context)
        circularProgress.strokeWidth = 10f
        circularProgress.centerRadius = 45f
        circularProgress.start()
        return circularProgress
    }

    class PictureAdapterViewHolder(
        val binding: PictureItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
