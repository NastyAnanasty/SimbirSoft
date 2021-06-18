package com.kas.simbirsoft.presenter.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kas.simbirsoft.databinding.ItemBusinessBinding
import com.kas.simbirsoft.extensions.setDateTime
import com.kas.simbirsoft.model.BusinessData
import com.kas.simbirsoft.model.TimeRange
import java.util.*

class BusinessRecyclerView() :
    RecyclerView.Adapter<BusinessRecyclerView.BusinessViewHolder>() {
    var clickListener: ((data: BusinessData) -> Unit)? = null

    private var listData: List<TimeRange> = emptyList()

    private fun inflateTimeRanges(date: Calendar): List<TimeRange> {
        val hoursRange = 9..20
        return hoursRange.map { hour ->
            TimeRange(setDateTime(date, hour), setDateTime(date, hour, 59))
        }
    }

    fun setBusiness(date: Calendar, list: List<BusinessData>) {
        listData = inflateTimeRanges(date)
        listData.forEach { it.addBusiness(list) }
        notifyDataSetChanged()
    }

    inner class BusinessViewHolder(private val binding: ItemBusinessBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { onClick(binding.timeRange?.business) }
        }

        fun bind(timeRange: TimeRange) {
            binding.timeRange = timeRange
        }

        private fun onClick(item: BusinessData?) {
            item?.let {
                clickListener?.invoke(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBusinessBinding =
            ItemBusinessBinding.inflate(layoutInflater, parent, false)
        return BusinessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}