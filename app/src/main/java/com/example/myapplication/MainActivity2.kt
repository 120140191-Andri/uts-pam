package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    companion object{
        const val namadat = "";
    }

    private lateinit var nama: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        nama = findViewById(R.id.textView2)

        val namad = intent.getStringExtra(namadat)

        nama.text = namad;

    }
}