package com.br.buscarcep.data.api

class EnderecoHelperApi(private val enderecoService: EnderecoService) {

    suspend fun getEndereco(cep: String) = enderecoService.buscarEndereco(cep)

}