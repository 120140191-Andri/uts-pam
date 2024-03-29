package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivitySplashBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.keRegis.setOnClickListener {
            pindahKeRegis(binding)
        }

    }

    private fun pindahKeRegis(binding: ActivityLoginBinding){
        val elemen = binding.loginLayout
        elemen.alpha = 1f

        elemen.animate().setDuration(1000).alpha(0f).withEndAction {
            val intentRegis = Intent(this, Registrasi::class.java)
            startActivity(intentRegis)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }
    }
}