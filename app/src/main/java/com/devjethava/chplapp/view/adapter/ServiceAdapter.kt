package com.devjethava.chplapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devjethava.chplapp.R
import com.devjethava.chplapp.databinding.RawProductBinding
import com.devjethava.chplapp.databinding.RawServiceBinding
import com.devjethava.chplapp.model.data.response.ProductData
import com.devjethava.chplapp.model.data.response.ServicesData

class ServiceAdapter(private val listener: (ServicesData) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstServiceData = ArrayList<ServicesData>()

    fun setServiceData(items: ArrayList<ServicesData>) {
        lstServiceData = items
        for (service in lstServiceData) {
            service.isChecked = false
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RawServiceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return lstServiceData.size
    }

    inner class ViewHolder(val binding: RawServiceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ServicesData, listener: (ServicesData) -> Unit, position: Int
        ) {
            binding.tvTitle.text = item.serviceName

            Glide.with(binding.root).load(item.serviceIcon).centerCrop()
                .error(R.drawable.broken_image).placeholder(R.drawable.broken_image)
                .into(binding.ivProduct)

            binding.rbSelect.isChecked = item.isChecked
            binding.rlRoot.background = ContextCompat.getDrawable(
                binding.root.context, if (item.isChecked) {
                    R.drawable.selected_background
                } else {
                    R.drawable.unselected_background
                }
            )

            binding.rlRoot.setOnClickListener {
                item.isChecked = !item.isChecked
                notifyItemChanged(position)
            }

        }
    }


    fun getData(): ArrayList<ServicesData> {
        return lstServiceData.filter { it.isChecked } as ArrayList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(lstServiceData[position], listener, position)
    }

}