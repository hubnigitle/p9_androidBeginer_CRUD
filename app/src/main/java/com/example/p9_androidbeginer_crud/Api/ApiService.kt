package com.example.p9_androidbeginer_crud.Api

import com.example.p9_androidbeginer_crud.Models.CreateUpdateProductRequest
import com.example.p9_androidbeginer_crud.Models.CreateUpdateProductResponse
import com.example.p9_androidbeginer_crud.Models.ProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService{
    @GET("products")
    fun getAllProducts(): Call<ProductResponse>

    @POST("products/add")
    fun addProduct(
        @Body product: CreateUpdateProductRequest
    ): Call<CreateUpdateProductResponse>
}