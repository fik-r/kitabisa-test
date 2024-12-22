package com.example.kitabisa_assesment.domain.abstraction.datasource

import com.example.kitabisa_assesment.domain.model.UniversityDomainModel

interface AppRemoteDataSource {
    suspend fun getList(): List<UniversityDomainModel>
}