package uz.gita.dima.palapan.presenter.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.dima.palapan.data.local.database.entity.MyData
import uz.gita.dima.palapan.databinding.ItemSozBinding
import javax.inject.Inject

class MyAdapter @Inject constructor() : ListAdapter<MyData, MyAdapter.MyViewHolder>(DIFF_CALL_BACK) {
    inner class MyViewHolder(private val binding: ItemSozBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData) {
            binding.apply {
                textQq.text = myData.qq
                textRus.text = myData.rus
                textEng.text = myData.eng
            }
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<MyData>() {
            override fun areItemsTheSame(oldItem: MyData, newItem: MyData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyData, newItem: MyData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSozBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}