package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityDashboardBinding
import com.example.myapplication.databinding.ActivityProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profil : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i("TAG", currentUser.uid)

            val db = FirebaseFirestore.getInstance()

            db.collection("users")
                .document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        // Dokumen ditemukan, Anda dapat mengakses data di sini
                        val data = document.data
                        // Lakukan apa pun yang perlu Anda lakukan dengan data di sini
                        println("Data: $data")

                        binding.hurufDepan.text = document.getString("nama")?.first().toString()
                        binding.nama.text = "Nama: ${document.getString("nama")}"
                        binding.email.text = "Email: ${document.getString("email")}"
                        binding.git.text = "Username Git: ${document.getString("usernamegit")}"
                        binding.nim.text = "nim: ${document.getString("nim")}"

                    } else {
                        // Dokumen tidak ditemukan
                        println("Document not found")
                    }
                }
                .addOnFailureListener { exception ->
                    // Kegagalan saat mengambil data
                    println("Error getting documents: $exception")
                    pindahKeLogin()
                }

        }else{
            pindahKeLogin()
        }

        binding.logoutBtn.setOnClickListener {
            logout()
        }

        binding.dashboardBtn2.setOnClickListener {
            pindahKeDashboard()
        }

    }

    private fun pindahKeDashboard(){
        val intentLogin = Intent(this, Dashboard::class.java)
        startActivity(intentLogin)
    }

    private fun pindahKeLogin(){
        val intentLogin = Intent(this, Login::class.java)
        startActivity(intentLogin)
    }

    private  fun logout(){
        auth.signOut()
        pindahKeLogin()
    }
}