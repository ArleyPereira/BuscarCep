package com.br.buscarcep.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.buscarcep.data.api.EnderecoHelperApi
import com.br.buscarcep.data.repository.EnderecoRepository
import com.br.buscarcep.ui.addEndereco.AddEnderecoViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val enderecoHelperApi: EnderecoHelperApi) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(AddEnderecoViewModel::class.java))
            AddEnderecoViewModel(EnderecoRepository(enderecoHelperApi)) as T
        else
            throw IllegalArgumentException("ViewModel Not Found")

    }
}