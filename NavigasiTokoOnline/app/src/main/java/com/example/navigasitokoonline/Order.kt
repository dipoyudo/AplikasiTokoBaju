package com.example.navigasitokoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_order.*

class Order : AppCompatActivity() {

    var totHarga    :Int    = 0
    var minteger    :Int    = 0
    var MIN_NUMBER          = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent          = intent
        val aProduct        =intent.getStringExtra("pProduct")
        val aHarga          =intent.getIntExtra("pHarga",0)
        val aImg            =intent.getIntExtra("pImg",0)

        actionBar.setTitle("Order")
        //id pada activity_order xml
        OrdProduct.text = aProduct
        OrdPrice.text = aHarga.toString()
        imageOrd.setImageResource(aImg)

        fun display(number:Int){
            val displayInteger = findViewById<View>(R.id.JmlOrd) as TextView

            displayInteger.text = ""+number

            totHarga = OrdPrice.text.toString().toInt() *
                    displayInteger.text.toString().toInt()
            TotHarOrd.text = totHarga.toString()
        }

        decreaseOrd.setOnClickListener(){
            if(minteger == MIN_NUMBER){
                minteger = 0
            }
            else{
                minteger = minteger - 1
                display(minteger)
            }
        }

        increaseOrd.setOnClickListener(){
            minteger = minteger + 1
            display(minteger)
        }
        OrderLagi.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity ::class.java)
            startActivity(intent)
        }

        Bayar.setOnClickListener {
            // GO TO INTENT BAYAR
            val i = Intent(this, DataPembeli::class.java)
            i.putExtra("name",aProduct)
            i.putExtra("price", aHarga.toString().toInt())
            i.putExtra("qty",JmlOrd.text.toString().toInt())
            i.putExtra("tot",totHarga.toString().toInt())

            startActivity(i)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
