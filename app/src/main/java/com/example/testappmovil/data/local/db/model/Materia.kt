package com.example.testappmovil.data.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materias")
data class Materia(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String
)
