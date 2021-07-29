package com.br.buscarcep.ui.home

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
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enderecoService = EnderecoService.getInstance()
        val enderecoRepository = EnderecoRepository(enderecoService)

        homeViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(enderecoRepository)
        ).get(HomeViewModel::class.java)

        homeViewModel.endereco.observe(viewLifecycleOwner, { endereco ->
            Log.i("INFOTESTE", "onCreate: $endereco")
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
        })

        homeViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        btnBuscar.setOnClickListener {
            homeViewModel.getEndereco(edtCep.text.toString())
        }

    }

}