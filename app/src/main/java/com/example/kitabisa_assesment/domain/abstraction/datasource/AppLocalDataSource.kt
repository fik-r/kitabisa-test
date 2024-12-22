package com.example.kitabisa_assesment.domain.abstraction.datasource

import com.example.kitabisa_assesment.domain.model.UniversityDomainModel

interface AppLocalDataSource {
    suspend fun addList(list: List<UniversityDomainModel>): List<Long>
    suspend fun getList(): List<UniversityDomainModel>
}