package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var nama: TextView
    private lateinit var email: TextView
    private lateinit var alamat: TextView
    private lateinit var hp: TextView
    private lateinit var btnKirim: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nama = findViewById(R.id.nama)
        email = findViewById(R.id.email)
        alamat = findViewById(R.id.alamat)
        hp = findViewById(R.id.hp)
        btnKirim = findViewById(R.id.button)

        btnKirim.setOnClickListener{
            val intentTujuan = Intent(this@MainActivity, MainActivity2::class.java);
            intentTujuan.putExtra(MainActivity2.namadat, nama.text.toString())
            startActivity(intentTujuan);
        }

    }
}