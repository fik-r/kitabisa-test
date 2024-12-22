package com.example.kitabisa_assesment.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "university")
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val webPages: String = "",
    val country: String = ""
)