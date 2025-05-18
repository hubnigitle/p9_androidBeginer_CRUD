package com.example.p9_androidbeginer_crud.Api

import com.example.p9_androidbeginer_crud.Models.CreateUpdateProductRequest
import com.example.p9_androidbeginer_crud.Models.CreateUpdateProductResponse
import com.example.p9_androidbeginer_crud.Models.DeleteProductResponse
import com.example.p9_androidbeginer_crud.Models.ProductResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService{
    @GET("products")
    fun getAllProducts(): Call<ProductResponse>

    @POST("products/add")
    fun addProduct(
        @Body product: CreateUpdateProductRequest
    ): Call<CreateUpdateProductResponse>

    @PUT("products/{id}")
    fun updateProduct(
        @Body product: CreateUpdateProductRequest,
        @Path("id") id: Int
    ): Call<CreateUpdateProductResponse>

    @DELETE("products/{id}")
    fun deleteProduct(
        @Path("id") id: Int
    ): Call<DeleteProductResponse>
}