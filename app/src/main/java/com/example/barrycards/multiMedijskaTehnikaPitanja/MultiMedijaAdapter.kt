package com.example.barrycards.multiMedijskaTehnikaPitanja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MultiMedijaAdapter : RecyclerView.Adapter<MultiMedijaAdapter.MultiMedijaHolder>() {

    private var multiMedijskaPitanja: List<MultiMedijskaPitanja> = ArrayList()
    private var listener: OnItemClickListenerMultiMedijskaPitanja? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiMedijaHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.barrycards.R.layout.sva_pitanja_item, parent, false)
        return MultiMedijaHolder(itemView)
    }

    override fun onBindViewHolder(holder: MultiMedijaHolder, position: Int) {
        val trentnaPitanja: MultiMedijskaPitanja = multiMedijskaPitanja[position]
        holder.textViewPitanje.text = trentnaPitanja.pitanje
        holder.textViewOdgovor.text = trentnaPitanja.odgovor
    }

    override fun getItemCount(): Int {
        return multiMedijskaPitanja.size
    }

    fun setMultiMedijaList(multiMedijskaPitanja: List<MultiMedijskaPitanja?>?) {
        this.multiMedijskaPitanja = multiMedijskaPitanja as List<MultiMedijskaPitanja>
        notifyDataSetChanged()
    }

    fun getMultiMedijaAt(position: Int): MultiMedijskaPitanja {
        return multiMedijskaPitanja[position]
    }

    inner class MultiMedijaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPitanje: TextView
        val textViewOdgovor: TextView

        init {
            textViewPitanje = itemView.findViewById(com.example.barrycards.R.id.view_pitanje)
            textViewOdgovor = itemView.findViewById(com.example.barrycards.R.id.view_odgovor)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) //NO_POSITION je uvijek -1
                    listener!!.onItemClickMultiMedijska(multiMedijskaPitanja[position])
            }
        }
    }

    interface OnItemClickListenerMultiMedijskaPitanja {
        fun onItemClickMultiMedijska(multiMedijskaPitanja: MultiMedijskaPitanja)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerMultiMedijskaPitanja?) {
        this.listener = listener
    }



}