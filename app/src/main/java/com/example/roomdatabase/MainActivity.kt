package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext,ContactDatabase::class.java,"contactDB").build()

        GlobalScope.launch {
            database.contactDao().insert(Contact(0,"Hassan","03030558418"))
        }
    }

    fun getData(view: View) {
        database.contactDao().getContact().observe(this) {
            for(i in it)
            {
                Toast.makeText(this,i.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
}