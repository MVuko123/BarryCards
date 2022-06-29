package com.example.barrycards.drugaGodina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.barrycards.prvaGodina.PrvaGodinaKolegiji

class DrugaGodinaAdapter : RecyclerView.Adapter<DrugaGodinaAdapter.DrugaGodinaHolder> () {
    private var drugiKolegiji: List<DrugaGodinaKolegiji> = ArrayList()
    private var listener: OnItemClickListenerDrugiKolegiji? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugaGodinaHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.barrycards.R.layout.sve_godine_item, parent, false)
        return DrugaGodinaHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrugaGodinaHolder, position: Int) {
        val trenutniDrugiKolegiji: DrugaGodinaKolegiji = drugiKolegiji[position]
        holder.textViewTitle.text = trenutniDrugiKolegiji.naziv
        holder.textViewNositelj.text = trenutniDrugiKolegiji.nositelj
        holder.textViewECTS.text = trenutniDrugiKolegiji.ects
    }

    override fun getItemCount(): Int {
        return  drugiKolegiji.size
    }

    fun setDrugiKolegijiList(drugiKolegiji: List<DrugaGodinaKolegiji?>?) {
        this.drugiKolegiji = drugiKolegiji as List<DrugaGodinaKolegiji>
        notifyDataSetChanged()
    }

    fun getDrugiKolegijiAt(position: Int): DrugaGodinaKolegiji {
        return drugiKolegiji[position]
    }

    inner class DrugaGodinaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                    listener!!.onItemClickDrugiKolegiji(drugiKolegiji[position])
            }
        }
    }

    interface OnItemClickListenerDrugiKolegiji {
        fun onItemClickDrugiKolegiji(drugaGodinaKolegiji: DrugaGodinaKolegiji?)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerDrugiKolegiji?){
        this.listener = listener
    }
}