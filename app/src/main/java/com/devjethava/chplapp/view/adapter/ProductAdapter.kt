package com.devjethava.chplapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devjethava.chplapp.R
import com.devjethava.chplapp.databinding.RawDashboardBinding
import com.devjethava.chplapp.databinding.RawProductBinding
import com.devjethava.chplapp.model.data.response.ProductData
import com.devjethava.chplapp.model.data.response.SubCategoryData

class ProductAdapter(private val listener: (ProductData) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstProductData = emptyList<ProductData>()

    fun setProductData(items: List<ProductData>) {
        lstProductData = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RawProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return lstProductData.size
    }

    inner class ViewHolder(val binding: RawProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ProductData, listener: (ProductData) -> Unit, position: Int
        ) {
            binding.tvTitle.text = item.productName

            Glide.with(binding.root).load(item.productPhoto).centerCrop()
                .error(R.drawable.broken_image).placeholder(R.drawable.broken_image)
                .into(binding.ivProduct)

            binding.mbAdd.setOnClickListener {
                listener.invoke(item)
            }

        }
    }


    fun getData(): List<ProductData> {
        return lstProductData
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(lstProductData[position], listener, position)
    }

}