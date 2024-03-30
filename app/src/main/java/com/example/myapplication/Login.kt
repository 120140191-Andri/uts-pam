package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.keRegis.setOnClickListener {
            pindahKeRegis(binding)
        }

        binding.button2.setOnClickListener {
            cobaTambahAkun()
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

    private fun cobaTambahAkun() {
        val firestore = FirebaseFirestore.getInstance()
        Log.i("TAG", "mulai")
        auth.createUserWithEmailAndPassword("admin", "password")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userData = hashMapOf(
                        "nama" to "tesnama",
                        "nim" to "120140191"
                    )
                    user?.let {
                        firestore.collection("users").document(it.uid)
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Registration successful.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { e ->
                                Log.i("Gagal", "${e.message}")
                                Toast.makeText(
                                    baseContext, "Registration failed. ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                } else {
                    Log.i("Gagal", "${task.exception?.message}")
                    Toast.makeText(
                        baseContext, "Registration failed. ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}