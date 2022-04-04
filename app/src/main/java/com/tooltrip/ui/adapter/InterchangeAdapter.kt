package com.tooltrip.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tooltrip.data.entities.Interchange
import com.tooltrip.databinding.ListItemInterchangeBinding
import com.tooltrip.ui.viewholder.InterchangeViewholder

class InterchangeAdapter(
    private val context: Context,
    private val items: MutableList<Interchange>,
    private val clickListener: (Interchange) -> Unit
) : RecyclerView.Adapter<InterchangeViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterchangeViewholder {

        val inflatedView =
            ListItemInterchangeBinding.inflate(LayoutInflater.from(context), parent, false)
        return InterchangeViewholder(inflatedView, clickListener)
    }

    override fun onBindViewHolder(holder: InterchangeViewholder, position: Int) {
        val items = items[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}