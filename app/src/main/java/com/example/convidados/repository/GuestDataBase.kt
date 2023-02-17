package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {

        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table Guest ("+
                "id interger primary key autoincrement, "+
                "name text," +
                "presence interger);")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}