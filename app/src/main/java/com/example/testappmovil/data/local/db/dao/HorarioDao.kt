package com.example.testappmovil.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testappmovil.data.local.db.model.Horario

@Dao
interface HorarioDao {
    @Insert
    suspend fun insert(horario: Horario)

    @Query("SELECT * FROM horarios")
    suspend fun getAll(): List<Horario>

    @Query("SELECT * FROM horarios WHERE dia = :dia")
    suspend fun getHorariosByDia(dia: String): List<Horario>
}
