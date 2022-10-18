package com.example.hamahama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hamahama.databinding.BookBinding

class Book : AppCompatActivity() {
    private var mBinding: BookBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = BookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}