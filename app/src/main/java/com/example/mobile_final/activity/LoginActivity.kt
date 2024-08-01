package com.example.mobile_final.activity

import android.content.Intent
import android.os.Bundle
import com.example.mobile_final.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }
}