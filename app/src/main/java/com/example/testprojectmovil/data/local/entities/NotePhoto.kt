package com.example.testprojectmovil.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_photos",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE // Si borras la materia, se borran sus fotos
        )
    ]
)
data class NotePhoto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subjectId: Long, // FK (Relación)
    val imagePath: String, // Ruta del archivo
    val timeStamp: Long // Fecha y hora de la foto
)