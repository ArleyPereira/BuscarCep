package com.br.buscarcep.data.repository

import com.br.buscarcep.service.EnderecoService

class EnderecoRepository(private val enderecoService: EnderecoService) {

    suspend fun getEndereco(cep: String) = enderecoService.buscarEndereco(cep)

}