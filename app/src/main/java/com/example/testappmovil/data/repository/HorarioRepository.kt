package com.example.testappmovil.data.repository

import com.example.testappmovil.data.local.db.dao.HorarioDao
import com.example.testappmovil.data.local.db.model.Horario

class HorarioRepository(private val horarioDao: HorarioDao) {

    suspend fun getAllHorarios(): List<Horario> {
        return horarioDao.getAll()
    }

    suspend fun getHorariosByDia(dia: String): List<Horario> {
        return horarioDao.getHorariosByDia(dia)
    }

    suspend fun insertHorario(horario: Horario) {
        horarioDao.insert(horario)
    }
}
