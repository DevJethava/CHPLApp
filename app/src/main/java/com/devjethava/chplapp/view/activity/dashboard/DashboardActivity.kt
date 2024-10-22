package com.devjethava.chplapp.view.activity.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.devjethava.chplapp.R
import com.devjethava.chplapp.database.entity.UserEntity
import com.devjethava.chplapp.databinding.ActivityDashboardBinding
import com.devjethava.chplapp.helper.Constants
import com.devjethava.chplapp.helper.Utils
import com.devjethava.chplapp.helper.toast
import com.devjethava.chplapp.model.data.response.DashboardResponse
import com.devjethava.chplapp.model.data.response.ProductData
import com.devjethava.chplapp.model.data.response.ServicesData
import com.devjethava.chplapp.view.activity.CartSummaryActivity
import com.devjethava.chplapp.view.adapter.DashboardAdapter
import com.devjethava.chplapp.view.base.BaseActivity
import com.devjethava.chplapp.view.dialog.BookServiceDialog
import com.devjethava.chplapp.viewmodel.DashboardViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.UUID

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {

    private val TAG = DashboardActivity::class.java.simpleName
    private val viewModel: DashboardViewModel by viewModel()

    private var adapter: DashboardAdapter? = null

    private var dashboardResponse: DashboardResponse? = null

    override fun initOnCreate() {
        super.initOnCreate()

        binding.toolbar.tvTitle.text = ContextCompat.getString(this, R.string.lbl_select_clothes)
        binding.toolbar.mbBack.isVisible = false

        binding.toolbar.rlCart.setOnClickListener {
            goToCart()
        }

        binding.toolbar.mbCart.setOnClickListener {
            goToCart()
        }

        binding.toolbar.fabCounter.setOnClickListener {
            goToCart()
        }

        getDashboardData()

        /**
         * 2: type of call API from ViewModel
         */
//        viewModel.getDashboardData2()
//        viewModel.getDashboardResponse.observe(this) {
//            it?.let { response -> {
//
//            } }
//        }
//
//        viewModel.exception.observe(this) {
//            Utils.printLog(TAG, it.toString())
//        }

        /**
         * open fragment and Follow Only One Activity architecture
         */
//        Intent(this, HolderActivity::class.java).apply {
//            putExtra(Constants.FRAGMENT, BlankFragment::class.java.simpleName)
//            startActivity(this)
//        }
//
//        Intent(this, HolderActivity::class.java).apply {
//            putExtra(Constants.FRAGMENT, BlankFragment2::class.java.simpleName)
//            putExtra(Constants.DATA_TO_SEND, BlankFragment2::class.java.simpleName)
//            startActivity(this)
//        }

        /**
         * How to call Fragment with HolderActivity
         */
//        Intent(this, HolderActivity::class.java).apply {
//            putExtra(Constants.FRAGMENT, BlankFragment2::class.simpleName)
//            putExtra(Constants.DATA_TO_SEND, BlankFragment2::class.simpleName)
//            startActivity(this)
//        }
    }

    private fun goToCart() {
        val intent = Intent(this, CartSummaryActivity::class.java)
        val cart = CartModel()
        cart.lstProductData = viewModel.lstProductData
        cart.lstServiceData = viewModel.lstServiceData
        intent.putExtra(Constants.DATA_TO_SEND, Gson().toJson(cart))
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun getDashboardData() {
        if (Utils.isConnectionAvailable(this)) {
            val progressDialog: AlertDialog = Utils.setupLoadingDialog(this)
            progressDialog.show()
            viewModel.getDashboardData().bindLifeCycle(this).subscribe({
                progressDialog.dismiss()
                it?.let { response ->
                    if (response.status.isNullOrEmpty()) {
                        toast(response.message.toString())
                    } else {
                        toast(response.message.toString())
                        dashboardResponse = response

                        adapter = DashboardAdapter(fun(product: ProductData) {

                            response.services?.let {
                                BookServiceDialog(
                                    response.services,
                                    fun(serviceList: ArrayList<ServicesData>) {
                                        val serviceName = StringBuffer()
                                        for (service in serviceList) {
                                            serviceName.append(service.serviceName.toString())
                                                .append(",")
                                        }
                                        viewModel.lstProductData.add(product)
                                        viewModel.lstServiceData.add(serviceName.toString())

                                        binding.toolbar.fabCounter.text =
                                            viewModel.lstProductData.size.toString()

                                        Log.e(TAG, viewModel.lstProductData.size.toString())
                                        Log.e(TAG, viewModel.lstServiceData.size.toString())
                                    }).show(
                                    supportFragmentManager, TAG
                                )
                            }
                        })
                        binding.rvDashboard.adapter = adapter
                        response.subCategories?.let {
                            adapter?.setSubCategoryData(it)
                        }
                        response.products?.let {
                            adapter?.setProductData(it)
                        }

                    }
                }
            }, {
                Utils.printLog(TAG, it.toString())
                toast(it.toString())
                progressDialog.dismiss()
                dispatchFailure(it, null, viewModel)
            })
        } else {
            toast(getString(R.string.internet_not_available))
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun clearOnDestroy() {
        super.clearOnDestroy()
        viewModel.lstServiceData.clear()
        viewModel.lstServiceData.clear()
    }
}