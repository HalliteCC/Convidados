package com.example.convidados.viewmodel

import androidx.lifecycle.ViewModel
import com.example.convidados.repository.GuestRepository

class GuestViewModel: ViewModel () {

    private val guestRepository = GuestRepository.getInstance()

    fun abc(){

    }

}