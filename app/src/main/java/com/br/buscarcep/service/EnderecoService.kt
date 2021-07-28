package com.br.buscarcep.service

import com.br.buscarcep.data.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("{cep}/json")
    fun buscarEndereco(@Path("cep") cep: String) : Call<Endereco>

}