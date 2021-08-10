package com.br.buscarcep.data.db.dao

import com.br.buscarcep.data.db.entity.AddressEntity
import com.br.buscarcep.data.repository.AddressRepositoryDataSource

class AddressDataSource(
    private val addressDao: AddressDao
) : AddressRepositoryDataSource {
    override suspend fun insert(addressEntity: AddressEntity): Long {
        return addressDao.insert(addressEntity)
    }

    override suspend fun update(addressEntity: AddressEntity) {
        addressDao.update(addressEntity)
    }

    override suspend fun delete(id: Long) {
        addressDao.delete(id)
    }

    override suspend fun deleteAll() {
        addressDao.deleteAll()
    }

    override suspend fun getAll(): List<AddressEntity> {
        return addressDao.getAll()
    }
}