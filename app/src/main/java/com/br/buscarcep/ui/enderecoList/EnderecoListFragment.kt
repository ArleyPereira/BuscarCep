package com.br.buscarcep.ui.enderecoList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.buscarcep.R
import com.br.buscarcep.data.db.entity.AddressEntity
import kotlinx.android.synthetic.main.endereco_list_fragment.*

class EnderecoListFragment : Fragment() {

    private val args: EnderecoListFragmentArgs by navArgs()

    private lateinit var enderecoListAdapter: EnderecoListAdapter
    private var addressEntityList: MutableList<AddressEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.endereco_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()

        setupUI()

        getEndereco()


    }

    private fun setupUI() {
        enderecoListAdapter = EnderecoListAdapter {
            Log.i("INFOTESTE", "setupUI: - adapter iniciado")
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = enderecoListAdapter
        }
    }

    private fun listeners() {
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_enderecoListFragment_to_addEnderecoFragment)
        }

    }

    // Recupera o endereço cadastrado
    private fun getEndereco() {
        args.endereco?.let { endereco ->
            addressEntityList.add(endereco)
            enderecoListAdapter.submitList(addressEntityList)
        }

    }

}