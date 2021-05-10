package com.example.navigasitokoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navDrawerView: NavigationView

    //Intialise the Navigation
    private lateinit var bottomNavigation : BottomNavigationView

    var myAdapter : ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayItem = ArrayList<ProductModel>()
        arrayItem.add(ProductModel("Baju Biru","Special Edition", R.drawable.bajubiru, 100000))
        arrayItem.add(ProductModel("Baju Hijau","Special Edition", R.drawable.bajuhijau, 100000))
        arrayItem.add(ProductModel("Baju Merah","Special Edition", R.drawable.bajumerah, 100000))
        arrayItem.add(ProductModel("Baju Kuning","Special Edition", R.drawable.bajukuning, 100000))
        arrayItem.add(ProductModel("Baju Hitam","Catoon Combad", R.drawable.bajuhitam, 200000))
        arrayItem.add(ProductModel("Baju Putih","Caton Combad", R.drawable.bajuputih, 200000))
        arrayItem.add(ProductModel("Baju Coklat","Caton Combad", R.drawable.bajucoklat, 200000))
        arrayItem.add(ProductModel("Baju AbuAbu","Plastisol", R.drawable.bajuabuabu, 300000))
        arrayItem.add(ProductModel("Baju Cream","Plastisol", R.drawable.bajucrame, 300000))
        arrayItem.add(ProductModel("Baju Garis","Plastisol", R.drawable.bajugaris, 300000))

        myAdapter = ProductAdapter(this)
        myAdapter!!.setData(arrayItem)

        //product recycleview berawal dari id recycleview pada activity_main
        product_recycleview.layoutManager = LinearLayoutManager(this)
        product_recycleview.adapter       = myAdapter
        //END PRODUCT ITEM

        //START BOTTOM NAVIGATION
        bottomNavigation = findViewById(R.id.navBotom)
        bottomNavigation.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.naviagation_home -> {
                    val intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.history ->{
                    Toast.makeText(this,"Go To History",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
        drawerLayout = findViewById(R.id.drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle.syncState()

        navDrawerView = findViewById(R.id.navDrawer)
        navDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myProfile -> {
                    val intent = Intent(applicationContext, Profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.myContact -> {
                    Toast.makeText(this, "Go to my Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.myHelp -> {
                    Toast.makeText(this, "Go to my Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
         if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Int.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(filterString: String?): Boolean {
                    myAdapter!!.filter.filter(filterString)
                    return true
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.shooping) {
            Toast.makeText(this,"View Shooping Chart", Toast.LENGTH_SHORT).show()
            return true
        } else if (id == R.id.account) {
            Toast.makeText(this,"Account Clicked", Toast.LENGTH_SHORT).show()
            return true
        } else if (id == R.id.logout) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            Toast.makeText(this,"Logout and go to login form", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}