package com.br.buscarcep.ui.enderecoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.br.buscarcep.R
import com.br.buscarcep.data.db.entity.AddressEntity
import kotlinx.android.synthetic.main.layout_item_endereco.view.*

class EnderecoListAdapter(
    private val onClick: (AddressEntity) -> Unit
) :
    ListAdapter<AddressEntity, EnderecoListAdapter.EnderecoViewHolder>(EnderecoListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnderecoViewHolder {
        return EnderecoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EnderecoViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class EnderecoViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(addressEntity: AddressEntity, click: ((AddressEntity) -> Unit)) {

            itemView.setOnClickListener { click(addressEntity) }

            with(itemView) {
                txtLocalidade.text =
                    context.getString(R.string.text_localidade, addressEntity.localidade)
                txtBairro.text =
                    context.getString(R.string.text_bairro, addressEntity.bairro)
                txtDDD.text =
                    context.getString(R.string.text_ddd, addressEntity.ddd)
                txtLogradouro.text =
                    context.getString(R.string.text_logradouro, addressEntity.logradouro)
                txtCEP.text =
                    context.getString(R.string.text_cep, addressEntity.cep)
            }
        }

        companion object {
            fun from(parent: ViewGroup): EnderecoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.layout_item_endereco, parent, false)
                return EnderecoViewHolder(view)
            }
        }
    }

    private companion object : DiffUtil.ItemCallback<AddressEntity>() {

        override fun areItemsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
            return oldItem.cep == newItem.cep
        }

        override fun areContentsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
            return oldItem == newItem
        }
    }
}