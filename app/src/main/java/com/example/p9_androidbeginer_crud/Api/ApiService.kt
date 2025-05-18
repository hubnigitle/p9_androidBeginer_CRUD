package com.example.p9_androidbeginer_crud.Api

import com.example.p9_androidbeginer_crud.Models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService{
    @GET("products")
    fun getAllProducts(): Call<ProductResponse>
}