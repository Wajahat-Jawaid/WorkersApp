package com.wajahat.workersapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    init {
        System.loadLibrary("keys")
    }

    private external fun getAuthHeader(): String
    private external fun getContentTypeHeader(): String
    private external fun getRequestedWithHeader(): String

    @AppScope
    @Provides
    fun okHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val original = chain.request()
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", getContentTypeHeader())
                    .addHeader("Authorization", getAuthHeader())
                    .addHeader("X-Requested-With", getRequestedWithHeader())

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .cache(cache).build()
    }

    @AppScope
    @Provides
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "cache_dir")
    }

    @AppScope
    @Provides
    fun cache(file: File): Cache {
        return Cache(file, 2 * 1000 * 1024) // 2 MB cache
    }

    @AppScope
    @Provides
    fun gson(): Gson {
        return GsonBuilder().setLenient().create()
    }
}