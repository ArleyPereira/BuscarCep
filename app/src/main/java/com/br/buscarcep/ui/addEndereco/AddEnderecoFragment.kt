package com.br.buscarcep.ui.addEndereco

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.br.buscarcep.R
import com.br.buscarcep.data.factory.MyViewModelFactory
import com.br.buscarcep.data.repository.EnderecoRepository
import com.br.buscarcep.service.EnderecoService
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

        val enderecoService = EnderecoService.getInstance()
        val enderecoRepository = EnderecoRepository(enderecoService)

        addEnderecoViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(enderecoRepository)
        ).get(AddEnderecoViewModel::class.java)

        addEnderecoViewModel.endereco.observe(viewLifecycleOwner, { endereco ->
            Log.i("INFOTESTE", "onCreate: $endereco")
        })

        addEnderecoViewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
        })

        addEnderecoViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        btnBuscar.setOnClickListener {
            addEnderecoViewModel.getEndereco(edtCep.text.toString())
        }

    }

}