package com.sibelyildiz.currencyexchange.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sibelyildiz.currencyexchange.model.CyrptoModel
import com.sibelyildiz.currencyexchangeapp.R
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(
    private val cyrptoList: ArrayList<CyrptoModel>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


    interface Listener {

        fun onItemClick(cyrptoModel: CyrptoModel)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cyrptoModel: CyrptoModel, listener: Listener) {

            itemView.setOnClickListener {
                listener.onItemClick(cyrptoModel)
            }
            itemView.text_name.text = cyrptoModel.currency
            itemView.text_price.text = cyrptoModel.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return cyrptoList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cyrptoList[position], listener)
    }

}

