package com.example.hamahama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hamahama.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MemberJoin : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    private var mBinding: LoginBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnJoin.setOnClickListener {
            signup()
        }
    }

    fun signup() {
        auth?.createUserWithEmailAndPassword(
            binding.emailEdittext.text.toString(),
            binding.passwordEdittext.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //Creating a user account
                    moveMainPage(task.result.user)
                } else if (task.exception?.message.isNullOrEmpty()) {
                    //show the error massage
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                } else {
                    //if already you have account
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,Login::class.java))
                }
            }
    }

    fun moveMainPage(user: FirebaseUser?){
        if(user != null){
            startActivity(Intent(this,MyPage::class.java))
        }
    }
}