package com.br.buscarcep.service

import com.br.buscarcep.data.model.Endereco
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json")
    suspend fun buscarEndereco(@Path("cep") cep: String): Response<Endereco>

    companion object {
        var enderecoService: EnderecoService? = null
        fun getInstance(): EnderecoService {
            if (enderecoService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://viacep.com.br/ws/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                enderecoService = retrofit.create(EnderecoService::class.java)
            }
            return enderecoService!!
        }
    }

}