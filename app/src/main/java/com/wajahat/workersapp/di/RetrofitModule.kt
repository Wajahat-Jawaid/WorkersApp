package com.wajahat.workersapp.di

import com.google.gson.Gson
import com.wajahat.workersapp.network.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class RetrofitModule {

    init {
        System.loadLibrary("keys")
    }

    private external fun getBaseURL(): String

    @AppScope
    @Provides
    fun retrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(getBaseURL())
            .build()
    }

    @AppScope
    @Provides
    fun apiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}