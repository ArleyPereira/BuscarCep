package com.br.buscarcep.data.repository

import com.br.buscarcep.data.api.AddressHelperApi

class EnderecoRepository(private val addressHelperApi: AddressHelperApi) {

    suspend fun getEndereco(cep: String) = addressHelperApi.getEndereco(cep)

}