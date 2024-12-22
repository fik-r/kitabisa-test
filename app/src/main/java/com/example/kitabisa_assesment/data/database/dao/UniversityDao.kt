package com.example.kitabisa_assesment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kitabisa_assesment.data.database.entity.UniversityEntity

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addList(list: List<UniversityEntity>): List<Long>

    @Query("Select * From university")
    suspend fun getList(): List<UniversityEntity>
}