package com.example.bankingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NAME TEXT, $COL_EMAIL TEXT UNIQUE, $COL_PASSWORD TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(name: String, email: String, password: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_NAME, name)
            put(COL_EMAIL, email)
            put(COL_PASSWORD, password)
        }
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }



    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "BankingAppDB"
        private const val TABLE_NAME = "users"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_EMAIL = "email"
        private const val COL_PASSWORD = "password"
    }
}
