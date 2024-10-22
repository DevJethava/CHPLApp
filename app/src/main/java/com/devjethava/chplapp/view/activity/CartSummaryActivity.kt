package com.devjethava.chplapp.view.activity

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.devjethava.chplapp.R
import com.devjethava.chplapp.databinding.ActivityCartSummaryBinding
import com.devjethava.chplapp.helper.Constants
import com.devjethava.chplapp.view.activity.dashboard.CartModel
import com.devjethava.chplapp.view.adapter.CartSummaryAdapter
import com.devjethava.chplapp.view.base.BaseActivity
import com.google.gson.Gson


class CartSummaryActivity :
    BaseActivity<ActivityCartSummaryBinding>(R.layout.activity_cart_summary) {

    private val TAG = CartSummaryActivity::class.java.simpleName

    override fun initOnCreate() {
        super.initOnCreate()

        binding.toolbar.rlCart.isVisible = false
        binding.toolbar.tvTitle.text = ContextCompat.getString(this, R.string.lbl_cart_summary)

        val cartDataString = intent.getStringExtra(Constants.DATA_TO_SEND)
        val cartData: CartModel = Gson().fromJson(cartDataString, CartModel::class.java)

        val adapter = CartSummaryAdapter(fun(position: Int) {

        })
        adapter.setCartData(cartData.lstProductData, cartData.lstServiceData)
        binding.rvCart.adapter = adapter

        binding.toolbar.mbBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun clearOnDestroy() {
        super.clearOnDestroy()
    }
}