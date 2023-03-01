package com.example.convidados.view.viewholder


import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.databinding.RowGuestBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.view.listener.OnGuestListener

//ViewHolder tem a referencia dos elementos do layout Row (lista)
class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name


        bind.buttonEdit.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.buttonDelete.setOnClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guest.id)}
                .setNegativeButton("Não", null)
                .create()
                .show()
        }

    }
}