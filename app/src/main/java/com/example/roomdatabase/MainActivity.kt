package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insert(Contact(0,"Hassan","03030558418", Date()))
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