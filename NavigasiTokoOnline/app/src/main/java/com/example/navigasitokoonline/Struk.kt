package com.example.navigasitokoonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_struk.*

class Struk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_struk)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val intent = intent

        val aNama = intent.getStringExtra("nm")
        val aTelp = intent.getStringExtra("telp")
        val aAlamat = intent.getStringExtra("alamat")
        val aProduct = intent.getStringExtra("name")
        val aHarga = intent.getIntExtra("price", 0)
        val aQty = intent.getIntExtra("qty", 0)
        val aTot = intent.getIntExtra("tot", 0)

        actionBar.setTitle("Struck" + aProduct)

        txtnama.text = aNama
        txtTelp.text = aTelp
        txtAlamat.text = aAlamat
        txtNamaProduct.text = aProduct
        txtHarga.text = aHarga.toString()
        txtQty.text = aQty.toString()
        txtTotHarga.text = aTot.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}