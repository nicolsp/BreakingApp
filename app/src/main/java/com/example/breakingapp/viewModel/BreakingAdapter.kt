package com.example.breakingapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breakingapp.R
import com.example.breakingapp.local.RealBreaking
import kotlinx.android.synthetic.main.breaking_list_view.view.*

class BreakingAdapter(val mPassTheData: PassTheData): RecyclerView.Adapter<BreakingAdapter.BreakingViewHolder>() {
    private var breakinginList = emptyList<RealBreaking>()

    fun updateAdapter(mList: List<RealBreaking>) {
        breakinginList = mList
        notifyDataSetChanged()
    }

    inner class BreakingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage = itemView.imageView1
        val name = itemView.textView1
        val clickListener = itemView.setOnClickListener {
            mPassTheData.passTheData(breakinginList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingAdapter.BreakingViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.breaking_list_view,parent,false)
        return BreakingViewHolder(itemView)
    }
    override fun getItemCount() =breakinginList.size

    interface PassTheData {
        fun passTheData(breaking: RealBreaking)
    }

    override fun onBindViewHolder(holder: BreakingViewHolder, position: Int) {
      val breaking = breakinginList[position]
        Glide.with(holder.itemView.context).load(breaking.img).into(holder.itemImage)
        holder.name.text = breaking.name
    }
}