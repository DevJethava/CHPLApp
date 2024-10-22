package com.devjethava.chplapp.model.remote

import com.devjethava.chplapp.model.data.response.DashboardResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApiService {
    // Get Dashboard
    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("cartResponse.json")
    fun getDashboardData(): Single<DashboardResponse>
}