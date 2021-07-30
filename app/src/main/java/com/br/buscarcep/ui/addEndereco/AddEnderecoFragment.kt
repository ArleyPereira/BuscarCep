package com.br.buscarcep.ui.addEndereco

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.br.buscarcep.R
import com.br.buscarcep.data.api.EnderecoHelperApi
import com.br.buscarcep.data.factory.ViewModelFactory
import com.br.buscarcep.data.repository.EnderecoRepository
import com.br.buscarcep.data.api.EnderecoService
import com.br.buscarcep.data.api.RetrofitBuilder
import com.br.buscarcep.utils.Status
import kotlinx.android.synthetic.main.add_endereco_fragment.*

class AddEnderecoFragment : Fragment() {

    private lateinit var addEnderecoViewModel: AddEnderecoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_endereco_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        setupObserver()

    }

    private fun setupViewModel() {
        addEnderecoViewModel = ViewModelProvider(
            this,
            ViewModelFactory(EnderecoHelperApi(RetrofitBuilder.enderecoService))
        ).get(AddEnderecoViewModel::class.java)
    }


    private fun setupObserver() {
        btnBuscar.setOnClickListener {
            addEnderecoViewModel.getEndereco(edtCep.text.toString()).observe(viewLifecycleOwner, {
                it?.let { resource ->

                    when (resource.status) {

                        Status.SUCESS -> {
                            Log.i("INFOTESTE", "setupObserver: ${resource.data}")
                            progressBar.visibility - View.GONE
                        }

                        Status.ERROR -> {
                            Log.i("INFOTESTE", "setupObserver: ${it.message}")
                            progressBar.visibility - View.GONE
                        }

                        Status.LOADING -> {
                            progressBar.visibility - View.VISIBLE
                        }

                    }

                }
            })
        }
    }

}