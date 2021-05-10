package com.example.navigasitokoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun loginMasuk(view: View) {
        val Uname   = username.text.toString()
        val Upass   = password.text.toString()

        if (Uname.isEmpty() || Upass.isEmpty()){
            Toast.makeText(this, "Ïsikan Username atau Password Dahulu !", Toast.LENGTH_SHORT).show()
        } else if (Uname.toLowerCase() == "dipo" && Upass.toLowerCase() == "1997"){
            Toast.makeText(this, "Login Sukses!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        } else {
            Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}