package com.example.testprojectmovil.data.repository


import com.example.testprojectmovil.data.local.dao.SubjectDao
import com.example.testprojectmovil.data.local.entities.NotePhoto
import com.example.testprojectmovil.data.local.entities.Subject
import com.example.testprojectmovil.data.local.entities.SubjectWithDetails
import com.example.testprojectmovil.data.local.entities.TextNote
import kotlinx.coroutines.flow.Flow

class SubjectRepository(private val subjectDao: SubjectDao) {

    // 1. Obtener todas las materias (el horario)
    val allSubjects: Flow<List<Subject>> = subjectDao.getAllSubjects()

    // 2. Obtener los detalles de una materia (fotos + notas)
    fun getSubjectDetails(id: Long): Flow<SubjectWithDetails> {
        return subjectDao.getSubjectWithDetails(id)
    }

    // 3. Guardar una nueva materia
    suspend fun insertSubject(subject: Subject) {
        subjectDao.insertSubject(subject)
    }

    // 4. Guardar una foto vinculada
    suspend fun insertPhoto(photo: NotePhoto) {
        subjectDao.insertPhoto(photo)
    }

    // 5. Guardar una nota de texto
    suspend fun insertNote(note: TextNote) {
        subjectDao.insertNote(note)
    }
}