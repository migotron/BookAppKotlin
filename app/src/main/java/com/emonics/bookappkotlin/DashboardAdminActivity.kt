package com.emonics.bookappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emonics.bookappkotlin.databinding.ActivityDashboardAdminBinding
import com.google.firebase.auth.FirebaseAuth

class DashboardAdminActivity : AppCompatActivity() {

    // view binding
    private lateinit var binding: ActivityDashboardAdminBinding

    // firebase auth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
            finish()
        }
    }

    private fun checkUser() {
        // get current user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser== null){
            // not logged in, goto main screen
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else {
            // logged in, get and show user info
            val email = firebaseUser.email
            //set to textView of toolbar
            binding.subTitleTv.text = email
        }
    }
}