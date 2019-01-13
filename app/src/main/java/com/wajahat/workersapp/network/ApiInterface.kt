package com.wajahat.workersapp.network

import com.wajahat.workersapp.api.GetWorkersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("workers")
    fun getWorkers(): Single<GetWorkersResponse>
}