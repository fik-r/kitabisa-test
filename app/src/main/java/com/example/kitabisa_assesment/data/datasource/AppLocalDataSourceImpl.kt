package com.example.kitabisa_assesment.data.datasource

import com.example.kitabisa_assesment.data.database.dao.UniversityDao
import com.example.kitabisa_assesment.data.mapper.entityToUniversityDomainModel
import com.example.kitabisa_assesment.data.mapper.toUniversityEntity
import com.example.kitabisa_assesment.domain.abstraction.datasource.AppLocalDataSource
import com.example.kitabisa_assesment.domain.model.UniversityDomainModel

class AppLocalDataSourceImpl(
    private val universityDao: UniversityDao
) : AppLocalDataSource {
    override suspend fun addList(list: List<UniversityDomainModel>): List<Long> =
        universityDao.addList(list.toUniversityEntity())


    override suspend fun getList(): List<UniversityDomainModel> =
        universityDao.getList().entityToUniversityDomainModel()
}