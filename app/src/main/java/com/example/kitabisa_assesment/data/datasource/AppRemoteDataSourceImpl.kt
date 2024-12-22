package com.example.kitabisa_assesment.data.datasource

import com.example.kitabisa_assesment.data.api.AppService
import com.example.kitabisa_assesment.data.mapper.responseToUniversityDomainModel
import com.example.kitabisa_assesment.domain.abstraction.datasource.AppRemoteDataSource
import com.example.kitabisa_assesment.domain.model.UniversityDomainModel
import retrofit2.HttpException

class AppRemoteDataSourceImpl(
    private val appService: AppService
) : AppRemoteDataSource {
    override suspend fun getList(): List<UniversityDomainModel> {
        val response = appService.getList()
        return if (response.isSuccessful) {
            response.body()?.responseToUniversityDomainModel().orEmpty()
        } else {
            throw HttpException(response)
        }
    }
}