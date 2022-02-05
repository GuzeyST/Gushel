package ru.guzeyst.gushelexamtinkoff.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.guzeyst.gushelexamtinkoff.domain.model.Picture

object PicturesDiffCallBack : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}