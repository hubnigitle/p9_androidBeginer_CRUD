package com.example.p9_androidbeginer_crud.Models

import com.google.gson.annotations.SerializedName

data class CreateUpdateProductRequest(

    @field:SerializedName("discountPercentage")
    val discountPercentage: Double? = null,

    @field:SerializedName("images")
    val images: List<String?>? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("rating")
    val rating: Double? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("stock")
    val stock: Int? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("brand")
    val brand: String? = null
)
