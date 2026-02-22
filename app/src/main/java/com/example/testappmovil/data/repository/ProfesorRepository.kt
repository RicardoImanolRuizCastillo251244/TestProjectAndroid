package com.example.testappmovil.data.repository

import com.example.testappmovil.data.local.db.dao.ProfesorDao
import com.example.testappmovil.data.local.db.model.Profesor

class ProfesorRepository(private val profesorDao: ProfesorDao) {

    suspend fun getAllProfesores(): List<Profesor> {
        return profesorDao.getAll()
    }

    suspend fun insertProfesor(profesor: Profesor) {
        profesorDao.insert(profesor)
    }
}
