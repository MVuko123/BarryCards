package com.example.barrycards.javaPitanja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.barrycards.programiranje1Pitanja.Programiranje1Pitanja

class JavaAdapter: RecyclerView.Adapter<JavaAdapter.JavaHolder> (){
    private var javaPitanja: List<JavaPitanja> = ArrayList()
    private var listener: OnItemClickListenerJavaPitanja? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JavaHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.barrycards.R.layout.sva_pitanja_item, parent, false)
        return JavaHolder(itemView)
    }

    override fun onBindViewHolder(holder: JavaHolder, position: Int) {
        val trentnaPitanja: JavaPitanja = javaPitanja[position]
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
        return javaPitanja.size
    }

    fun setJavaList(javaPitanja: List<JavaPitanja?>?) {
        this.javaPitanja = javaPitanja as List<JavaPitanja>
        notifyDataSetChanged()
    }

    fun getJavaAt(position: Int): JavaPitanja {
        return javaPitanja[position]
    }

    inner class JavaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                    listener!!.onItemClickJava(javaPitanja[position])
            }
        }
    }

    interface OnItemClickListenerJavaPitanja {
        fun onItemClickJava(javaPitanja: JavaPitanja)
    }

    fun setOnItemClickListener(listener: OnItemClickListenerJavaPitanja?) {
        this.listener = listener
    }
}