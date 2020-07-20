package com.sibelyildiz.currencyexchange.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sibelyildiz.currencyexchange.adapter.RecyclerViewAdapter
import com.sibelyildiz.currencyexchange.model.CyrptoModel
import com.sibelyildiz.currencyexchange.service.CryptoApi
import com.sibelyildiz.currencyexchangeapp.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https:api.nomics.com/v1//"
    var cryptoModels: ArrayList<CyrptoModel>? = null
    var recyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https:api.nomics.com/v1/prices?key=7d2f35aef2ff3d0de70d4a6667a76972

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }

    fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoApi::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CyrptoModel>> {
            override fun onFailure(call: Call<List<CyrptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<CyrptoModel>>,
                response: Response<List<CyrptoModel>>
            ) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                            recyclerView.adapter = recyclerViewAdapter
                        }


                    }
                }

            }

        })
    }

    override fun onItemClick(cyrptoModel: CyrptoModel) {
        Toast.makeText(this, "${cyrptoModel.currency} tıklandı.", Toast.LENGTH_LONG).show()
    }


}