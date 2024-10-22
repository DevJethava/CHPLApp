package com.devjethava.chplapp.view.activity.dashboard

import com.devjethava.chplapp.model.data.response.ProductData
import java.io.Serializable

class CartModel : Serializable {
    var lstProductData = ArrayList<ProductData>()
    var lstServiceData = ArrayList<String>()
}