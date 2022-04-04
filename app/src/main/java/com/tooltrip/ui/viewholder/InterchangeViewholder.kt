package com.tooltrip.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.tooltrip.data.entities.Interchange
import com.tooltrip.databinding.ListItemInterchangeBinding

class InterchangeViewholder(private val binding: ListItemInterchangeBinding,
                            private val clickListener: (Interchange) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(interchange: Interchange) {
        binding.durationTxt.text = interchange.name
        binding.durationTxt.setOnClickListener {
            clickListener(interchange)
        }
    }
}