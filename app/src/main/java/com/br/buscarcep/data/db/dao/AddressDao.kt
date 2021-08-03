package com.br.buscarcep.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.br.buscarcep.data.db.entity.AddressEntity

@Dao
interface AddressDao {
    @Insert
    suspend fun insert(addressEntity: AddressEntity): Long

    @Update
    suspend fun update(addressEntity: AddressEntity)

    @Query("DELETE from address WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM address")
    suspend fun deleteAll()

    @Query("SELECT * FROM address")
    suspend fun getAll(): List<AddressEntity>
}