package com.example.kitabisa_assesment.data.api

import com.example.kitabisa_assesment.data.api.model.UniversityResponse
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("/search?country=indonesia")
    suspend fun getList(): Response<List<UniversityResponse>>
}