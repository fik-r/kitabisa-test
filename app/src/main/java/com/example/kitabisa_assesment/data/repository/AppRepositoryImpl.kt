package com.example.kitabisa_assesment.data.repository

import com.example.kitabisa_assesment.domain.abstraction.datasource.AppLocalDataSource
import com.example.kitabisa_assesment.domain.abstraction.datasource.AppRemoteDataSource
import com.example.kitabisa_assesment.domain.abstraction.repository.AppRepository
import com.example.kitabisa_assesment.domain.model.UniversityDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepositoryImpl(
    private val remote: AppRemoteDataSource,
    private val local: AppLocalDataSource
) : AppRepository {
    override fun getList(): Flow<List<UniversityDomainModel>> = flow {
        val localData = local.getList()
        if (localData.isNotEmpty()) {
            emit(localData)
        } else {
            remote.getList()
                .takeIf { it.isNotEmpty() }
                ?.also { local.addList(it) }
                ?.let { emit(it) }
        }
    }
}