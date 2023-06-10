package com.example.marsneocasestudy.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsneocasestudy.R
import com.example.marsneocasestudy.Utils.CurrencyType
import com.example.marsneocasestudy.Utils.getCurrencyIcon
import com.example.marsneocasestudy.Utils.getCurrencyName
import com.example.marsneocasestudy.api.CurrencyData

class CurrencyAdapter(
    private var currencyList : MutableList<CurrencyData> = mutableListOf(),
    private var listener : (currencyData: CurrencyData ) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon = view.findViewById<ImageView>(R.id.currencyIcon)
        val currencyName = view.findViewById<TextView>(R.id.currencyName)
        val currencyPrice = view.findViewById<TextView>(R.id.currencyPrice)
        val upDown = view.findViewById<ImageView>(R.id.upOrDown)
        val button = view.findViewById<AppCompatButton>(R.id.buyButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_items, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    fun setItems(list : List<CurrencyData>){
        currencyList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.currencyName.text = getCurrencyName(holder.itemView.context, currencyList[position].currencyType)
        holder.currencyPrice.text = holder.itemView.context.getString(R.string.price, currencyList[position].value)
        holder.button.setOnClickListener {
            listener.invoke(currencyList[position])
        }

        Glide.with(holder.itemView)
            .load(getCurrencyIcon(holder.itemView.context, currencyList[position].currencyType))
            .into(holder.icon)

    }
}