package com.example.kitabisa_assesment.domain.abstraction.repository

import com.example.kitabisa_assesment.domain.model.UniversityDomainModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getList(): Flow<List<UniversityDomainModel>>
}