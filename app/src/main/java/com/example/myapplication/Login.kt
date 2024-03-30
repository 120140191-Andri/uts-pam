package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
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

        val currentUser = auth.currentUser
        if (currentUser != null) {
            pindahDashboard()
        }

        binding.keRegis.setOnClickListener {
            pindahKeRegis()
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            if(email != "" && password != ""){
                authLogin(email, password)
            }else{
                Toast.makeText(this, "Email dan Password Field Wajib Diisi", Toast.LENGTH_SHORT).show()
                recreate()
            }
        }

    }

    private fun pindahDashboard(){
        val intentDas = Intent(this, Dashboard::class.java)
        startActivity(intentDas)
    }

    private fun pindahKeRegis(){
        val intentRegis = Intent(this, Registrasi::class.java)
        startActivity(intentRegis)
    }

    private fun authLogin(email: String, password: String){
        Toast.makeText(
            this,
            "Masuk.",
            Toast.LENGTH_SHORT,
        ).show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Login Berhasil.",
                        Toast.LENGTH_SHORT,
                    ).show()

                    pindahDashboard()
                } else {
                    Toast.makeText(
                        this,
                        "Login Gagal.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    recreate()
                }
            }
    }
}