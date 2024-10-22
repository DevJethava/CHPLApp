package com.devjethava.chplapp.model.data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DashboardResponse() : Parcelable {
    @SerializedName("sub_categories")
    @Expose
    val subCategories: ArrayList<SubCategoryData>? = null

    @SerializedName("products")
    @Expose
    val products: ArrayList<ProductData>? = null

    @SerializedName("services")
    @Expose
    val services: ArrayList<ServicesData>? = null

    @SerializedName("status")
    @Expose
    val status: String? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DashboardResponse> {
        override fun createFromParcel(parcel: Parcel): DashboardResponse {
            return DashboardResponse(parcel)
        }

        override fun newArray(size: Int): Array<DashboardResponse?> {
            return arrayOfNulls(size)
        }
    }

}

class SubCategoryData() : Parcelable {
    @SerializedName("sub_category_id")
    @Expose
    val subCategoryId: String? = null

    @SerializedName("service_ids")
    @Expose
    val serviceIds: String? = null

    @SerializedName("regular_prices")
    @Expose
    val regularPrices: String? = null

    @SerializedName("parent_category_id")
    @Expose
    val parentCategoryId: String? = null

    @SerializedName("sub_category_name")
    @Expose
    val subCategoryName: String? = null

    @SerializedName("sub_category_photo")
    @Expose
    val subCategoryPhoto: String? = null

    @SerializedName("sub_category_active_status")
    @Expose
    val subCategoryActiveStatus: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubCategoryData> {
        override fun createFromParcel(parcel: Parcel): SubCategoryData {
            return SubCategoryData(parcel)
        }

        override fun newArray(size: Int): Array<SubCategoryData?> {
            return arrayOfNulls(size)
        }
    }
}

class ProductData() : Parcelable {
    @SerializedName("order_in_out")
    @Expose
    val orderInOut: String? = null

    @SerializedName("user_id")
    @Expose
    val userId: String? = null

    @SerializedName("user_full_name")
    @Expose
    val userFullName: String? = null

    @SerializedName("user_relation")
    @Expose
    val userRelation: String? = null

    @SerializedName("product_id")
    @Expose
    val productId: String? = null

    @SerializedName("sub_category_id")
    @Expose
    val subCategoryId: String? = null

    @SerializedName("product_name")
    @Expose
    val productName: String? = null

    @SerializedName("tag_code")
    @Expose
    val tagCode: String? = null

    @SerializedName("group_name")
    @Expose
    val groupName: String? = null

    @SerializedName("city_name")
    @Expose
    val cityName: String? = null

    @SerializedName("state_id")
    @Expose
    val stateId: String? = null

    @SerializedName("city_id")
    @Expose
    val cityId: String? = null

    @SerializedName("state_name")
    @Expose
    val stateName: String? = null

    @SerializedName("product_photo")
    @Expose
    val productPhoto: String? = null

    @SerializedName("product_active_status")
    @Expose
    val productActiveStatus: String? = null

    @SerializedName("in_out")
    @Expose
    val inOut: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductData> {
        override fun createFromParcel(parcel: Parcel): ProductData {
            return ProductData(parcel)
        }

        override fun newArray(size: Int): Array<ProductData?> {
            return arrayOfNulls(size)
        }
    }
}

class ServicesData() : Parcelable {
    @SerializedName("service_id")
    @Expose
    val serviceId: String? = null

    @SerializedName("service_name")
    @Expose
    val serviceName: String? = null

    @SerializedName("service_desc")
    @Expose
    val serviceDesc: String? = null

    @SerializedName("indexing")
    @Expose
    val indexing: String? = null

    @SerializedName("included_service")
    @Expose
    val includedService: String? = null

    @SerializedName("included_service_name")
    @Expose
    val includedServiceName: String? = null

    @SerializedName("service_base_price")
    @Expose
    val serviceBasePrice: String? = null

    @SerializedName("service_icon")
    @Expose
    val serviceIcon: String? = null

    @SerializedName("service_active_status")
    @Expose
    val serviceActiveStatus: String? = null


    var isChecked: Boolean = false

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ServicesData> {
        override fun createFromParcel(parcel: Parcel): ServicesData {
            return ServicesData(parcel)
        }

        override fun newArray(size: Int): Array<ServicesData?> {
            return arrayOfNulls(size)
        }
    }
}