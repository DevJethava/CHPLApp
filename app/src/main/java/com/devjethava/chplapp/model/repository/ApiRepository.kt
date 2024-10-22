package com.devjethava.chplapp.model.repository

import com.devjethava.chplapp.model.data.response.DashboardResponse
import com.devjethava.chplapp.model.remote.RestApiService
import io.reactivex.Single

interface ApiRepository {
    fun getDashboardData(): Single<DashboardResponse>
}

class ApiRepositoryImpl(
    private val remote: RestApiService,
) : ApiRepository {
    override fun getDashboardData(): Single<DashboardResponse> = remote.getDashboardData()
}

