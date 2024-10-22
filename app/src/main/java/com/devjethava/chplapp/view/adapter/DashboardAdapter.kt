package com.devjethava.chplapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devjethava.chplapp.databinding.RawDashboardBinding
import com.devjethava.chplapp.model.data.response.ProductData
import com.devjethava.chplapp.model.data.response.SubCategoryData

class DashboardAdapter(private val listener: (ProductData) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var lstSubCategoryData = emptyList<SubCategoryData>()
    var lstProductData = emptyList<ProductData>()

    fun setSubCategoryData(items: ArrayList<SubCategoryData>) {
        lstSubCategoryData = items
        notifyDataSetChanged()
    }

    fun setProductData(items: ArrayList<ProductData>) {
        lstProductData = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RawDashboardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return lstSubCategoryData.size
    }

    inner class ViewHolder(val binding: RawDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: SubCategoryData, listener: (ProductData) -> Unit, position: Int
        ) {
            binding.tvTitle.text = item.subCategoryName

            val categoryProductData = ArrayList<ProductData>()

            for (product in lstProductData) {
                if (product.subCategoryId == item.subCategoryId) {
                    categoryProductData.add(product)
                }
            }

            val adapter = ProductAdapter(fun(mProduct: ProductData) {
                listener.invoke(mProduct)
            })
            binding.rvProduct.adapter = adapter
            adapter.setProductData(categoryProductData)


        }
    }


    fun getData(): List<SubCategoryData> {
        return lstSubCategoryData
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(lstSubCategoryData[position], listener, position)
    }

}