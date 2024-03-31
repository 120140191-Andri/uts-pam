package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegistrasiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registrasi : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrasiBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.keLogin.setOnClickListener {
            pindahKeLogin()
        }

        binding.registrasiBtn.setOnClickListener {
            val nama = binding.inputNama.text.toString()
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            val usernamegit = binding.inputUsernameGit.text.toString()
            val nim = binding.inputNIM.text.toString()

            val inputs = mapOf(
                "nama" to nama,
                "email" to email,
                "password" to password,
                "usernamegit" to usernamegit,
                "nim" to nim,
            )

            val kosong = inputs.filterValues { it.isEmpty() }.keys
            if(kosong.isEmpty()){
                ubahStateLoading(true)
                tambahAkun(inputs)
            }else{
                recreate()
                Toast.makeText(this, "Semua Field Wajib Diisi!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun pindahKeLogin(){
        val intentLogin = Intent(this, Login::class.java)
        startActivity(intentLogin)
    }

    private fun tambahAkun(inputs: Map<String, String>) {

        auth.createUserWithEmailAndPassword(inputs["email"]!!, inputs["password"]!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    tambahDataLain(inputs)
                    ubahStateLoading(true)
                } else {
                    Log.i("Gagal", "${task.exception?.message}")
                    Toast.makeText(
                        baseContext, "Registration failed. ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    recreate()
                    ubahStateLoading(false)
                }
            }

    }

    private fun ubahStateLoading(st: Boolean){
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)

        if (st){
            binding.registrasiBtn.text = "Loading..."
        }else{
            binding.registrasiBtn.text = "Registrasi"
        }
    }

    private fun tambahDataLain(inputs: Map<String, String>){
        val firestore = FirebaseFirestore.getInstance()

        val user = auth.currentUser
        val userData = hashMapOf(
            "nama" to inputs["nama"],
            "email" to inputs["email"],
            "usernamegit" to inputs["usernamegit"],
            "nim" to inputs["nim"]
        )
        user?.let {
            firestore.collection("users").document(it.uid)
                .set(userData)
                .addOnSuccessListener {
                    ubahStateLoading(false)
                    Toast.makeText(
                        this,
                        "Registration Berhasil, Silahkan Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    ubahStateLoading(false)
                    pindahKeLogin()
                }
                .addOnFailureListener { e ->
                    ubahStateLoading(false)
                    recreate()
                    Log.i("Gagal", "${e.message}")
                    Toast.makeText(
                        baseContext, "Registration failed. ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    ubahStateLoading(false)
                }
        }
    }

}