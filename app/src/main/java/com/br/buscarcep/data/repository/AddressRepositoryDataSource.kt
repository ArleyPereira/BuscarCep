package com.br.buscarcep.data.repository

import com.br.buscarcep.data.db.entity.AddressEntity

interface AddressRepositoryDataSource {

    suspend fun insert(addressEntity: AddressEntity): Long

    suspend fun update(addressEntity: AddressEntity)

    suspend fun delete(id: Long)

    suspend fun deleteAll()

    suspend fun getAll(): List<AddressEntity>

}