package com.example.testprojectmovil.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.testprojectmovil.data.local.entities.NotePhoto
import com.example.testprojectmovil.data.local.entities.Subject
import com.example.testprojectmovil.data.local.entities.SubjectWithDetails
import com.example.testprojectmovil.data.local.entities.TextNote
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    // Insertar materia
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    // Obtener el horario completo (ordenado por hora)
    @Query("SELECT * FROM subjects ORDER BY startTime ASC")
    fun getAllSubjects(): Flow<List<Subject>>

    // La función que creamos antes para ver detalles, fotos y notas
    @Transaction
    @Query("SELECT * FROM subjects WHERE id = :subjectId")
    fun getSubjectWithDetails(subjectId: Long): Flow<SubjectWithDetails>

    // Insertar una foto
    @Insert
    suspend fun insertPhoto(photo: NotePhoto)

    // Insertar una nota de texto
    @Insert
    suspend fun insertNote(note: TextNote)
}