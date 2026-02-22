package com.example.testappmovil.data.local.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "imagenes",
    foreignKeys = [
        ForeignKey(entity = Materia::class, parentColumns = ["id"], childColumns = ["materiaId"])
    ]
)
data class Imagen(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val materiaId: Long,
    val ruta: String,
    val descripcion: String
)
