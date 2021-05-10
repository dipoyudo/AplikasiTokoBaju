package com.example.navigasitokoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_data_pembeli.*

class DataPembeli : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_pembeli)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val intent      = intent
        val aProduct    = intent.getStringExtra("name")
        val aHarga      = intent.getIntExtra("price", 0)
        val aQty        = intent.getIntExtra("qty", 0)
        val aTot        = intent.getIntExtra("tot", 0)

        actionBar.setTitle("Pembayaran " + aProduct)

        txtNamaProduk.text      = aProduct
        txtHarga.text           = aHarga.toString()
        txtQty.text             = aQty.toString()
        txtTotHarga.text        = aTot.toString()

        btnBayar.setOnClickListener{
            val i = Intent (this, Struk::class.java)
            i.putExtra("nm", txtnama1.text.toString())
            i.putExtra("telp", txtTlp1.text.toString())
            i.putExtra("alamat", txtAlamat1.text.toString())
            i.putExtra("name", aProduct)
            i.putExtra("price", aHarga.toString().toInt())
            i.putExtra("qty", aQty.toString().toInt())
            i.putExtra("tot", aTot.toString().toInt())

            startActivity(i)

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}