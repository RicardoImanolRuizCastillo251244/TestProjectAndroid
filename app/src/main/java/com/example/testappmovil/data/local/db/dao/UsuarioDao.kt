package com.example.testappmovil.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testappmovil.data.local.db.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE username = :username")
    suspend fun getUserByUsername(username: String): Usuario?
}
