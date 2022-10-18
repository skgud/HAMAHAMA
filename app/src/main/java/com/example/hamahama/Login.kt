package com.example.hamahama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hamahama.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Login : AppCompatActivity() {
    private var mBinding: LoginBinding? = null
    private val binding get() = mBinding!!
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnStore.setOnClickListener {
            var intent = Intent(this,Store::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            signinEmail()
        }

        binding.btnJoin.setOnClickListener {
            var intent = Intent(this,MemberJoin::class.java)
            startActivity(intent)
        }
    }

    fun signinEmail() {
        auth?.signInWithEmailAndPassword(
            binding.emailEdittext.text.toString(),
            binding.passwordEdittext.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //Creating
                    moveMainPage(task.result.user)
                } else {
                    //Show the error message
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                }
            }
    }
    fun moveMainPage(user:FirebaseUser?){
        if(user != null){
            startActivity(Intent(this,MyPage::class.java))
        }
    }
}
























