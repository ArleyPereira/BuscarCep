package com.br.buscarcep.data.api

class AddressHelperApi(private val addressService: AddressService) {

    suspend fun getEndereco(cep: String) = addressService.buscarEndereco(cep)

}