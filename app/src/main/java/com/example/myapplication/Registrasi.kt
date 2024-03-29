package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegistrasiBinding

class Registrasi : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.keLogin.setOnClickListener {
            pindahKeLogin(binding)
        }

    }

    private fun pindahKeLogin(binding: ActivityRegistrasiBinding){
        val elemen = binding.regisLayout
        elemen.alpha = 1f

        elemen.animate().setDuration(1000).alpha(0f).withEndAction {
            val intentLogin = Intent(this, Login::class.java)
            startActivity(intentLogin)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }
    }

}