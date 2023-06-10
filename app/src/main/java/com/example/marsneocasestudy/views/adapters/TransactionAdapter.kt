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
import com.example.marsneocasestudy.database.TransactionEntity

class TransactionAdapter(
    private var currencyList : MutableList<TransactionEntity> = mutableListOf()
) : RecyclerView.Adapter<TransactionAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon = view.findViewById<ImageView>(R.id.icon)
        val transactionName = view.findViewById<TextView>(R.id.name)
        val boughtPrice = view.findViewById<TextView>(R.id.boughtPrice)
        val date = view.findViewById<TextView>(R.id.date)
        val total = view.findViewById<TextView>(R.id.total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_items, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    fun setItems(list : List<TransactionEntity>){
        currencyList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {


        holder.transactionName.text = holder.itemView.context.getString(R.string.buy_text, getCurrencyName(holder.itemView.context, currencyList[position].type))
        holder.boughtPrice.text = holder.itemView.context.getString(R.string.price_bought, currencyList[position].buyPrice.toFloat())
        holder.date.text = currencyList[position].date
        holder.total.text = holder.itemView.context.getString(R.string.total, currencyList[position].amount)

        Glide.with(holder.itemView)
            .load(getCurrencyIcon(holder.itemView.context, currencyList[position].type))
            .into(holder.icon)
    }
}