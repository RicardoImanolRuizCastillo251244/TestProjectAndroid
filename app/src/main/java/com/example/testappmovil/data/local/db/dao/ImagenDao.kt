package com.example.testappmovil.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testappmovil.data.local.db.model.Imagen

@Dao
interface ImagenDao {
    @Insert
    suspend fun insert(imagen: Imagen)

    @Query("SELECT * FROM imagenes WHERE materiaId = :materiaId")
    suspend fun getImagesForMateria(materiaId: Long): List<Imagen>
}
