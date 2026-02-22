package com.example.testappmovil.data.repository

import com.example.testappmovil.data.local.db.dao.ImagenDao
import com.example.testappmovil.data.local.db.model.Imagen

class ImagenRepository(private val imagenDao: ImagenDao) {

    suspend fun getImagesForMateria(materiaId: Long): List<Imagen> {
        return imagenDao.getImagesForMateria(materiaId)
    }

    suspend fun insertImagen(imagen: Imagen) {
        imagenDao.insert(imagen)
    }
}
