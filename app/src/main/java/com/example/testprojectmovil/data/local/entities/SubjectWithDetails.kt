package com.example.testprojectmovil.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SubjectWithDetails(
    @Embedded val subject: Subject, // La entidad principal (Materia)

    @Relation(
        parentColumn = "id",    // ID de la tabla Subject
        entityColumn = "subjectId" // ID de referencia en NotePhoto
    )
    val photos: List<NotePhoto>, // Lista de fotos vinculadas

    @Relation(
        parentColumn = "id",    // ID de la tabla Subject
        entityColumn = "subjectId" // ID de referencia en TextNote
    )
    val notes: List<TextNote> // Lista de notas de texto vinculadas
)