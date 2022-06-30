package com.example.barrycards.trecaGodina

import android.R.attr.button
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView


class TrecaGodinaAdapter : RecyclerView.Adapter<TrecaGodinaAdapter.TrecaGodinaHolder>(){
    private var treciKolegiji: List<TrecaGodinaKolegiji> = ArrayList()
    private var listener: OnItemClickListenerTreciKolegiji? = null
    private var trecagodinaKolegiji: TrecaGodinaKolegiji? = null
    private var activityTrecaGodina: ActivityTrecaGodina? = null
    private var adapterTrecaGodina: TrecaGodinaAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrecaGodinaHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.barrycards.R.layout.sve_godine_item, parent, false)
        return TrecaGodinaHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrecaGodinaHolder, position: Int) {
        val trenutniTreciKolegiji: TrecaGodinaKolegiji = treciKolegiji[position]
        holder.textViewTitle.text = trenutniTreciKolegiji.naziv
        holder.textViewNositelj.text = trenutniTreciKolegiji.nositelj
        holder.textViewECTS.text = trenutniTreciKolegiji.ects



    }



    override fun getItemCount(): Int {
        return treciKolegiji.size
    }

    fun setTreciKolegijiList(treciKolegiji: List<TrecaGodinaKolegiji?>?) {
        this.treciKolegiji = treciKolegiji as List<TrecaGodinaKolegiji>
        notifyDataSetChanged()
    }

    fun getTreciKolegijiAt(position: Int): TrecaGodinaKolegiji {
        return treciKolegiji[position]
    }

    inner class TrecaGodinaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView
        val textViewNositelj: TextView
        val textViewECTS: TextView
        //var buttonDalje: AppCompatImageButton? = null

        init {
            textViewTitle = itemView.findViewById(com.example.barrycards.R.id.text_view_naziv)
            textViewNositelj = itemView.findViewById(com.example.barrycards.R.id.text_view_nositelj)
            textViewECTS = itemView.findViewById(com.example.barrycards.R.id.text_view_ECTS)
            //buttonDalje = itemView.findViewById(com.example.barrycards.R.id.button_dalje)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) //NO_POSITION je uvijek -1
                    listener!!.onItemClickTreciKolegiji(treciKolegiji[position])
            }
        }
    }

    interface OnItemClickListenerTreciKolegiji {
        fun onItemClickTreciKolegiji(trecaGodinaKolegiji: TrecaGodinaKolegiji)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerTreciKolegiji?) {
        this.listener = listener
    }

}