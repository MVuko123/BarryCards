package com.example.barrycards.programiranje1Pitanja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class Programiranje1Adapter : RecyclerView.Adapter<Programiranje1Adapter.Programiranje1Holder>() {

    private var programiranje1Pitanja: List<Programiranje1Pitanja> = ArrayList()
    private var listener: OnItemClickListenerProgramiranje1Pitanja? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Programiranje1Holder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.barrycards.R.layout.sva_pitanja_item, parent, false)
        return Programiranje1Holder(itemView)
    }

    override fun onBindViewHolder(holder: Programiranje1Holder, position: Int) {
        val trentnaPitanja: Programiranje1Pitanja = programiranje1Pitanja[position]
        holder.textViewPitanje.text = trentnaPitanja.pitanje
        holder.textViewOdgovor.text = trentnaPitanja.odgovor
        holder.buttonOkreni?.setOnClickListener{
            if(holder.textViewPitanje.visibility == View.VISIBLE){
                holder.textViewPitanje.visibility = View.INVISIBLE
            }else if(holder.textViewPitanje.visibility == View.INVISIBLE){
                holder.textViewPitanje.visibility = View.VISIBLE
            }

            if(holder.textViewOdgovor.visibility == View.VISIBLE){
                holder.textViewOdgovor.visibility = View.INVISIBLE
            }else if(holder.textViewOdgovor.visibility == View.INVISIBLE){
                holder.textViewOdgovor.visibility = View.VISIBLE
            }

        }
    }

    override fun getItemCount(): Int {
        return programiranje1Pitanja.size
    }

    fun setProgramiranje1List(programiranje1Pitanja: List<Programiranje1Pitanja?>?) {
        this.programiranje1Pitanja = programiranje1Pitanja as List<Programiranje1Pitanja>
        notifyDataSetChanged()
    }

    fun getProgramiranje1At(position: Int): Programiranje1Pitanja {
        return programiranje1Pitanja[position]
    }

    inner class Programiranje1Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPitanje: TextView
        val textViewOdgovor: TextView
        var buttonOkreni: AppCompatImageButton? = null



        init {
            textViewPitanje = itemView.findViewById(com.example.barrycards.R.id.view_pitanje)
            textViewOdgovor = itemView.findViewById(com.example.barrycards.R.id.view_odgovor)
            buttonOkreni = itemView.findViewById(com.example.barrycards.R.id.button_okreni)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) //NO_POSITION je uvijek -1
                    listener!!.onItemClickProgramiranje1(programiranje1Pitanja[position])
            }
        }
    }

    interface OnItemClickListenerProgramiranje1Pitanja {
        fun onItemClickProgramiranje1(programiranje1Pitanja: Programiranje1Pitanja)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerProgramiranje1Pitanja?) {
        this.listener = listener
    }

}