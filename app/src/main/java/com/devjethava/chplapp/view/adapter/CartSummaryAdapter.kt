package com.devjethava.chplapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devjethava.chplapp.R
import com.devjethava.chplapp.databinding.RawCartSummaryBinding
import com.devjethava.chplapp.databinding.RawDashboardBinding
import com.devjethava.chplapp.databinding.RawProductBinding
import com.devjethava.chplapp.helper.removeLastComma
import com.devjethava.chplapp.model.data.response.ProductData
import com.devjethava.chplapp.model.data.response.ServicesData
import com.devjethava.chplapp.model.data.response.SubCategoryData

class CartSummaryAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstProductData = emptyList<ProductData>()
    var lstServiceData = emptyList<String>()

    fun setCartData(products: ArrayList<ProductData>, services: ArrayList<String>) {
        lstProductData = products
        lstServiceData = services
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RawCartSummaryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return lstProductData.size
    }

    inner class ViewHolder(val binding: RawCartSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            product: ProductData, service: String, listener: (Int) -> Unit, position: Int
        ) {
            binding.tvProductTitle.text = product.productName

            Glide.with(binding.root).load(product.productPhoto).centerCrop()
                .error(R.drawable.broken_image).placeholder(R.drawable.broken_image)
                .into(binding.ivProduct)

            binding.tvServiceName.text = service.removeLastComma().replace(",", "\n")

            val dummyPrice = StringBuilder()
            for (i in service.removeLastComma().split(",").indices) {
                dummyPrice.append("$ 20.00").append("\n")
            }
            binding.tvServicePrice.text = dummyPrice.toString()

        }
    }


    fun getData(): List<ProductData> {
        return lstProductData
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(
            lstProductData[position], lstServiceData[position], listener, position
        )
    }

}