package com.example.magazin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, passwd TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addUser(user: User){
        val value = ContentValues()

        value.put("login", user.login)
        value.put("passwd", user.passwd)

        val db = this.writableDatabase
        db.insert("users", null, value)

        db.close()
    }

    fun getUser(login: String, passwd: String) : Boolean{
        val db = this.readableDatabase
        val res = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND passwd = '$passwd'", null)
        return res.moveToFirst()
    }
}