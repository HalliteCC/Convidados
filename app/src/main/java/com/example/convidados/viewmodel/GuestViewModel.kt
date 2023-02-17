package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestViewModel(application: Application): AndroidViewModel(application) {

    private val guestRepository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel){
        guestRepository.insert(guest)
    }

}