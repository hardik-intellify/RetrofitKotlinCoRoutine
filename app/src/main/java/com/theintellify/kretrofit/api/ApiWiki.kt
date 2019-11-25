/**
 *
 * Created by Hardik B. Mahant on 11/25/2019.
 * Developer EmailID : work.mahant@gmail.com
 * (c) 2019 The Intellify, ALL RIGHTS RESERVED
 * Industry EmailID : hello@theintellify.com
 */

package com.theintellify.kretrofit.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.theintellify.kretrofit.api.response.ApiResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Faking parameters as the API token (this is just for demo purpose)
private const val ACTION = "query"
private const val FORMAT = "json"
private const val SEARCH = "search"

interface ApiWiki {

    @GET("api.php")
    fun getResult(
        @Query("srsearch") searchString: String = "Android"
    ): Deferred<ApiResponse>

    //This could be done in a proper way
    companion object{
        operator fun invoke(): ApiWiki{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request().url().newBuilder()
                    .addQueryParameter("action", ACTION)
                    .addQueryParameter("format", FORMAT)
                    .addQueryParameter("list", SEARCH)
                    .build()
                val request = chain.request().newBuilder()
                    .url(url).build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder().client(okHttpClient).baseUrl("https://en.wikipedia.org/w/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiWiki::class.java)
        }
    }
}