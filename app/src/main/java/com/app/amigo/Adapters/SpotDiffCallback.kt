package com.app.amigo.Adapters

import androidx.recyclerview.widget.DiffUtil
import com.app.amigo.Models.Trends.Userlist.TrendsDatum

class SpotDiffCallback(
        private val old: List<TrendsDatum>,
        private val new: List<TrendsDatum>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}
