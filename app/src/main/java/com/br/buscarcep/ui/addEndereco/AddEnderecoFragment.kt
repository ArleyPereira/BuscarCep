package com.br.buscarcep.ui.addEndereco

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.br.buscarcep.R
import com.br.buscarcep.data.api.AddressHelperApi
import com.br.buscarcep.data.api.RetrofitBuilder
import com.br.buscarcep.data.factory.ViewModelFactory
import com.br.buscarcep.utils.Status
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.add_endereco_fragment.*

class AddEnderecoFragment : Fragment() {

    private lateinit var addEnderecoViewModel: AddEnderecoViewModel
    private lateinit var firebaseAnalytics: FirebaseAnalytics

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

        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
    }

    private fun setupViewModel() {
        addEnderecoViewModel = ViewModelProvider(
            this,
            ViewModelFactory(AddressHelperApi(RetrofitBuilder.ADDRESS_SERVICE))
        ).get(AddEnderecoViewModel::class.java)
    }

    private fun setupObserver() {
        btnBuscar.setOnClickListener {

            val params = Bundle()
            params.putString("image_name", "TESTE1")
            params.putString("full_text", "TESTE2")
            firebaseAnalytics.logEvent("share_image", params)

            val msg: String? = null
            Toast.makeText(requireContext(), msg!!.substring(0, 2), Toast.LENGTH_SHORT ).show()

            addEnderecoViewModel.getEndereco(edtCep.text.toString())
                .observe(viewLifecycleOwner, { endereco ->
                    endereco?.let { resource ->

                        when (resource.status) {
                            Status.SUCESS -> {
                                Toast.makeText(
                                    activity,
                                    "Endereço cadastrado com sucesso!",
                                    Toast.LENGTH_LONG
                                ).show()

                                val action = AddEnderecoFragmentDirections
                                    .actionAddEnderecoFragmentToEnderecoListFragment(endereco.data)

                                findNavController().navigate(action)

                            }

                            Status.ERROR -> {
                                Toast.makeText(activity, endereco.message, Toast.LENGTH_LONG).show()
                                progressBar.visibility - View.GONE
                            }

                            Status.LOADING -> {
                                Log.i("INFOTESTE", "setupObserver: - Carregando")
                                progressBar.visibility - View.VISIBLE
                            }

                        }

                    }
                })
        }
    }

}