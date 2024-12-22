package com.example.kitabisa_assesment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kitabisa_assesment.data.database.dao.UniversityDao
import com.example.kitabisa_assesment.data.database.entity.UniversityEntity

@Database(entities = [UniversityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}