package com.example.kitabisa_assesment.data.api.model

import com.google.gson.annotations.SerializedName

data class UniversityResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("web_pages")
    val webPages: List<String>? = null,
    @SerializedName("country")
    val country: String? = null
)