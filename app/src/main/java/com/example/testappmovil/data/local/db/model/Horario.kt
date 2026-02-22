package com.example.testappmovil.data.local.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "horarios",
    foreignKeys = [
        ForeignKey(entity = Materia::class, parentColumns = ["id"], childColumns = ["materiaId"]),
        ForeignKey(entity = Profesor::class, parentColumns = ["id"], childColumns = ["profesorId"])
    ]
)
data class Horario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val materiaId: Long,
    val profesorId: Long,
    val dia: String,
    val horaInicio: Int,
    val horaFin: Int
)
