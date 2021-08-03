package com.br.buscarcep.data.api

import com.br.buscarcep.data.db.entity.AddressEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("{cep}/json")
    suspend fun buscarEndereco(@Path("cep") cep: String): AddressEntity

}