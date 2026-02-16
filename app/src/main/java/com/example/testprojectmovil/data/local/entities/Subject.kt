package com.example.testprojectmovil.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val professorName: String,
    val dayOfWeek: Int, // 1 para Lunes, 2 para Martes, etc.
    val startTime: String, // Ejemplo: "08:00"
    val endTime: String, // Ejemplo: "09:30"
    val color: Int // Para diferenciar materias visualmente
)