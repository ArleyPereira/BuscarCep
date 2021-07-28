package com.br.buscarcep

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.buscarcep.data.model.Endereco
import com.br.buscarcep.service.EnderecoService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicia Retrofit
        initRetrofit()

        listeners()

    }

    private fun listeners(){
        btnBusar.setOnClickListener {

            val cep = edtCep.text.toString().trim()

            if(cep.isNotEmpty()){
                buscarCep(cep)
            }else {
                edtCep.requestFocus()
                edtCep.error = "Informe o CEP."
            }

        }
    }

    private fun initRetrofit() {
        retrofit = Retrofit
            .Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun buscarCep(cep: String){

        progressBar.visibility = View.VISIBLE

        val cepService = retrofit.create(EnderecoService::class.java)
        val call = cepService.buscarEndereco(cep)

        call.enqueue(object : Callback<Endereco>{
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                if(response.isSuccessful){
                    Log.i("INFOTESTE", "onResponse: ${response.body()}")
                }else {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}