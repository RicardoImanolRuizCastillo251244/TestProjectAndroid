package com.example.testappmovil.data.repository

import com.example.testappmovil.data.local.db.dao.MateriaDao
import com.example.testappmovil.data.local.db.model.Materia

class MateriaRepository(private val materiaDao: MateriaDao) {

    suspend fun getAllMaterias(): List<Materia> {
        return materiaDao.getAll()
    }

    suspend fun insertMateria(materia: Materia) {
        materiaDao.insert(materia)
    }
}
