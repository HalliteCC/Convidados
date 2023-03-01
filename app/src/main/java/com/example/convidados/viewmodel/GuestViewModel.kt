package com.example.convidados.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository
import kotlin.coroutines.coroutineContext

class GuestViewModel(application: Application): AndroidViewModel(application) {

    private val guestRepository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel


    fun get(id: Int){
        guestModel.value = guestRepository.get(id)
    }

    fun insert(guest: GuestModel){
       guestRepository.insert(guest)
    }

    fun update(guest: GuestModel){
        guestRepository.update(guest)
    }

}