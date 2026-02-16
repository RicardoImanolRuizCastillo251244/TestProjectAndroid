package com.example.testprojectmovil.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "text_notes",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TextNote(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subjectId: Long, // FK (Relación)
    val title: String,
    val content: String,
    val timeStamp: Long
)