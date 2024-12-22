package com.example.kitabisa_assesment.data.mapper

import com.example.kitabisa_assesment.data.api.model.UniversityResponse
import com.example.kitabisa_assesment.data.database.entity.UniversityEntity
import com.example.kitabisa_assesment.domain.model.UniversityDomainModel

fun UniversityEntity.toDomainModel() = UniversityDomainModel(
    name = name,
    webPages = listOf(webPages),
    country = country
)

fun UniversityResponse.toDomainModel() = UniversityDomainModel(
    name = name.orEmpty(),
    webPages = webPages.orEmpty(),
    country = country.orEmpty()
)

fun UniversityDomainModel.toEntity() = UniversityEntity(
    name = name,
    webPages = webPages.firstOrNull().orEmpty(),
    country = country
)

fun List<UniversityEntity>.entityToUniversityDomainModel(): List<UniversityDomainModel> =
    map { it.toDomainModel() }

fun List<UniversityResponse>.responseToUniversityDomainModel(): List<UniversityDomainModel> =
    map { it.toDomainModel() }

fun List<UniversityDomainModel>.toUniversityEntity(): List<UniversityEntity> =
    map { it.toEntity() }
