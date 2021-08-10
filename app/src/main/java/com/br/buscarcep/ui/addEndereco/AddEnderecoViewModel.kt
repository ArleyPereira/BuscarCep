package com.br.buscarcep.ui.addEndereco

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.br.buscarcep.data.repository.EnderecoRepository
import com.br.buscarcep.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.net.ConnectException

class AddEnderecoViewModel(private val enderecoRepository: EnderecoRepository) : ViewModel() {

    fun getEndereco(cep: String) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.sucess(data = enderecoRepository.getEndereco(cep)))
        } catch (e: ConnectException) {
            emit(Resource.error(data = null, message = "Falha na comunicação com API"))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Aconteceu um erro!"))
        }

    }

}