package com.example.testprojectmovil.data.local

import androidx.room.Database
import com.example.testprojectmovil.data.local.dao.SubjectDao
import com.example.testprojectmovil.data.local.entities.NotePhoto
import com.example.testprojectmovil.data.local.entities.Subject
import com.example.testprojectmovil.data.local.entities.TextNote
import androidx.room.RoomDatabase


@Database(
    entities = [Subject::class, NotePhoto::class, TextNote::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
}