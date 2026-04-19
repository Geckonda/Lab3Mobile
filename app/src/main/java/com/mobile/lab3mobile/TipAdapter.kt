package com.mobile.lab3mobile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TipAdapter(private val tips: List<Tip>) :
    RecyclerView.Adapter<TipAdapter.TipViewHolder>() {

    private val expandedItems = mutableSetOf<Int>()

    inner class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayLabel: TextView = itemView.findViewById(R.id.tvDayLabel)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val image: ImageView = itemView.findViewById(R.id.ivTipImage)
        val description: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tips[position]

        holder.dayLabel.text = holder.itemView.context.getString(R.string.day_label, tip.day)
        holder.title.text = tip.title
        holder.image.setImageResource(tip.imageResId)
        holder.description.text = tip.shortDescription

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java).apply {
                putExtra("day", tip.day)
                putExtra("title", tip.title)
                putExtra("description", tip.description)
                putExtra("imageResId", tip.imageResId)
            }
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = tips.size
}
