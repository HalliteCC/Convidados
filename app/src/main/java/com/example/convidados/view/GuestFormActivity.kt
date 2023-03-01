package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestViewModel: GuestViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        guestViewModel = ViewModelProvider(this).get(GuestViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresence.isChecked = true

        observe()
        loadData()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked



            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            toast(model)
        }
    }

    private fun observe(){
        guestViewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence){
                binding.radioPresence.isChecked = true

            }else {
                binding.radioAbsent.isChecked = true
            }
        })
    }

    private fun loadData(){
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            guestViewModel.get(guestId)
        }
    }

    fun toast (guest: GuestModel){

        val name = binding.editName.text.toString()

        //Verificação se já existe o convidado
        if (guestId == 0 &&  name != "") {
            guestViewModel.insert(guest)
            Toast.makeText(applicationContext, "Convidado Criado", Toast.LENGTH_SHORT).show()
            finish()
        }else if (guestId != 0 && name != "") {
            guestViewModel.update(guest)
            Toast.makeText(applicationContext, "Convidado Modificado", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            Toast.makeText(applicationContext, "FALHA", Toast.LENGTH_SHORT).show()
        }
    }
}