package com.nyt.nytimes.di

import android.content.Context
import com.nyt.nytimes.BuildConfig
import com.nyt.nytimes.NYTimesApp
import com.nyt.nytimes.R
import com.nyt.nytimes.rest.APIInterface
import com.nyt.nytimes.utils.NetworkConnection
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    single { getOkHttpClient() }
    single { getRetrofit(get()) }
    single { getApiService(get()) }
    single { getNetworkHelper(androidContext()) }
}

private fun getRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(NYTimesApp.instance.getString(R.string.base_url))
        .client(okHttpClient)
        .build()



private fun getOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
    } else OkHttpClient
        .Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

private fun getNetworkHelper(context: Context) = NetworkConnection(context)

private fun getApiService(retrofit: Retrofit): APIInterface =
    retrofit.create(APIInterface::class.java)