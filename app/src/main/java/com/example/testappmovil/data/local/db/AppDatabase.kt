package com.example.testappmovil.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testappmovil.data.local.db.dao.HorarioDao
import com.example.testappmovil.data.local.db.dao.ImagenDao
import com.example.testappmovil.data.local.db.dao.MateriaDao
import com.example.testappmovil.data.local.db.dao.ProfesorDao
import com.example.testappmovil.data.local.db.dao.UsuarioDao
import com.example.testappmovil.data.local.db.model.Horario
import com.example.testappmovil.data.local.db.model.Imagen
import com.example.testappmovil.data.local.db.model.Materia
import com.example.testappmovil.data.local.db.model.Profesor
import com.example.testappmovil.data.local.db.model.Usuario

@Database(
    entities = [Materia::class, Profesor::class, Horario::class, Imagen::class, Usuario::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun materiaDao(): MateriaDao
    abstract fun profesorDao(): ProfesorDao
    abstract fun horarioDao(): HorarioDao
    abstract fun imagenDao(): ImagenDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "organizador_academico_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
