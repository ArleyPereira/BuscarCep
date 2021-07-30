package com.br.buscarcep.ui.addEndereco

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.buscarcep.data.model.Endereco
import com.br.buscarcep.data.repository.EnderecoRepository
import kotlinx.coroutines.*

class AddEnderecoViewModel constructor(
    private val enderecoRepository: EnderecoRepository
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val endereco = MutableLiveData<Endereco>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getEndereco(cep: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = enderecoRepository.getEndereco(cep)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    endereco.postValue(response.body())
                    loading.value = false
                }else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}