package com.example.barrycards

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.String
import kotlinx.android.synthetic.main.activity_prva_godina.*
import kotlin.Int


class PrvaGodinaAdapter : RecyclerView.Adapter<PrvaGodinaAdapter.PrvaGodinaHolder>() {
    private var prviKolegiji: List<PrvaGodinaKolegiji> = ArrayList()
    private var listener: OnItemClickListenerPrviKolegiji? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrvaGodinaHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.barrycards.R.layout.prva_godina_item, parent, false)
        return PrvaGodinaHolder(itemView)
    }

    override fun onBindViewHolder(holder: PrvaGodinaHolder, position: Int) {
        val trenutniPrviKolegiji: PrvaGodinaKolegiji = prviKolegiji[position]
        holder.textViewTitle.text = trenutniPrviKolegiji.naziv
        holder.textViewNositelj.text = trenutniPrviKolegiji.nositelj
        holder.textViewECTS.text = trenutniPrviKolegiji.ects
    }

    override fun getItemCount(): Int {
        return prviKolegiji.size
    }

    fun setPrviKolegijiList(prviKolegiji: List<PrvaGodinaKolegiji?>?) {
        this.prviKolegiji = prviKolegiji as List<PrvaGodinaKolegiji>
        notifyDataSetChanged()
    }

    fun getPrviKolegijiAt(position: Int): PrvaGodinaKolegiji {
        return prviKolegiji[position]
    }

    inner class PrvaGodinaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView
        val textViewNositelj: TextView
        val textViewECTS: TextView

        init {
            textViewTitle = itemView.findViewById(com.example.barrycards.R.id.text_view_naziv)
            textViewNositelj = itemView.findViewById(com.example.barrycards.R.id.text_view_nositelj)
            textViewECTS = itemView.findViewById(com.example.barrycards.R.id.text_view_ECTS)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) //NO_POSITION je uvijek -1
                    listener!!.onItemClickPrviKolegiji(prviKolegiji[position])
            }
        }
    }

    interface OnItemClickListenerPrviKolegiji {
        fun onItemClickPrviKolegiji(prvaGodinaKolegiji: PrvaGodinaKolegiji?)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerPrviKolegiji?) {
        this.listener = listener
    }
}

