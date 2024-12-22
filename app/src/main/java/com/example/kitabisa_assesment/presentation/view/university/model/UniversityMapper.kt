package com.example.kitabisa_assesment.presentation.view.university.model

import com.example.kitabisa_assesment.domain.model.UniversityDomainModel

fun UniversityDomainModel.toUIModel() = University(
    title = name,
    location = country,
    url = webPages.firstOrNull().orEmpty()
)

fun University.toDomainModel() = UniversityDomainModel(
    name = title,
    country = location,
    webPages = listOf(url)
)

fun List<UniversityDomainModel>.domainToUniversityUIModel(): List<University> =
    map { it.toUIModel() }

fun List<University>.uiToUniversityDomainModel(): List<UniversityDomainModel> =
    map { it.toDomainModel() }