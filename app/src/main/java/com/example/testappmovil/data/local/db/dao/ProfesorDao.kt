package com.example.testappmovil.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testappmovil.data.local.db.model.Profesor

@Dao
interface ProfesorDao {
    @Insert
    suspend fun insert(profesor: Profesor)

    @Query("SELECT * FROM profesores")
    suspend fun getAll(): List<Profesor>
}
