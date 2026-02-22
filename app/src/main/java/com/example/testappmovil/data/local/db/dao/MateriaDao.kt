package com.example.testappmovil.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testappmovil.data.local.db.model.Materia

@Dao
interface MateriaDao {
    @Insert
    suspend fun insert(materia: Materia)

    @Query("SELECT * FROM materias")
    suspend fun getAll(): List<Materia>
}
