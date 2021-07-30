package com.br.buscarcep.data.api

import com.br.buscarcep.data.model.Endereco
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json")
    suspend fun buscarEndereco(@Path("cep") cep: String): Endereco

}