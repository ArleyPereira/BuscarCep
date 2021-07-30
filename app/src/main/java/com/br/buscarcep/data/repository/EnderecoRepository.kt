package com.br.buscarcep.data.repository

import com.br.buscarcep.data.api.EnderecoHelperApi

class EnderecoRepository(private val enderecoHelperApi: EnderecoHelperApi) {

    suspend fun getEndereco(cep: String) = enderecoHelperApi.getEndereco(cep)

}