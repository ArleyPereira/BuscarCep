package com.br.buscarcep.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.buscarcep.data.repository.EnderecoRepository
import com.br.buscarcep.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class MyViewModelFactory constructor(
    private val repository: EnderecoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}