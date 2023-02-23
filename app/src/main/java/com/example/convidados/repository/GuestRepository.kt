package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.Data
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.model.GuestModel
import kotlinx.coroutines.selects.select

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)


    //Singleton

    companion object {
        private lateinit var repository: GuestRepository


        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMNS.PRESENCE, presence)


            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)

            true

        } catch (e: java.lang.Exception) {
            false
        }

    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)

            true

        } catch (e: java.lang.Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase


            val selection = DataBaseConstants.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            true

        } catch (e: java.lang.Exception) {
            false
        }

    }

    fun getAll(): List<GuestModel> {

        //definição da lista
        val list = mutableListOf<GuestModel>()

        try {
            //abertura do banco de dados
            val db = guestDataBase.readableDatabase

            //seleções
            val selection = arrayOf(
                DataBaseConstants.COLUMNS.ID,
                DataBaseConstants.COLUMNS.NAME,
                DataBaseConstants.COLUMNS.PRESENCE
            )

            //realização e execução do cursor
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, selection,
                null, null, null, null, null
            )

            //mapeamento dos dados
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }
            //fechamento do cursor
            cursor.close()

        }catch (e: Exception){
            return list
        }
            return list
    }
}


