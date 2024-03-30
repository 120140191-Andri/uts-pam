package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tampilLogo(binding)
    }

    private fun tampilLogo(binding: ActivitySplashBinding) {
        binding.logo.alpha = 0f
        binding.logo.animate().setDuration(3000).alpha(1f).withEndAction{
            setelah3Detik()
        }
    }

    private fun setelah3Detik() {

        if (cekLogin()){
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }else{
            val intentDash = Intent(this, Login::class.java)
            startActivity(intentDash)
        }

    }

    private fun cekLogin(): Boolean {
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i("TAG", currentUser.uid)
            return true
        }else{
            Log.i("TAG", "kosong")
            return false
        }
    }

}