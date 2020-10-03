package com.example.e11evenyarid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    val firebaseAuth = FirebaseAuth.getInstance()
    val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(app_bar_layout_in_use as Toolbar?)
        supportActionBar?.title = "Yarid"
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerlayout_wrapper,
            R.string.draweropen,
            R.string.drawerclose
        )
        drawerlayout_wrapper.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle?.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()


    }

    private fun init() {
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    val preferences = this.getSharedPreferences(
                        "Stage1Details",
                        Context.MODE_PRIVATE
                    )
                    preferences?.edit()?.remove("useremail")?.commit()
                    Toast.makeText(this, "Bye Bye", Toast.LENGTH_LONG).show()
                    mAuth?.signOut()
                    startActivity(
                        Intent(
                            this,
                            AuthActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                    finish()
                }
                R.id.settings -> {
                    Toast.makeText(this, "settings", Toast.LENGTH_LONG).show()
                }
                R.id.findfriends -> {
                    Toast.makeText(this, "findfriends", Toast.LENGTH_LONG).show()
                }
                R.id.Friends -> {
                    Toast.makeText(this, "Friends", Toast.LENGTH_LONG).show()
                }
                R.id.Home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.maincontainer, HomeDrawerFragment()).commit()
                    Toast.makeText(this, "Home", Toast.LENGTH_LONG).show()
                }
                R.id.profile -> {
                    Toast.makeText(this, "profiprofilele", Toast.LENGTH_LONG).show()
                }
                R.id.addnewpost -> {
                    Toast.makeText(this, "addnewpost", Toast.LENGTH_LONG).show()
                }

            }

            drawerlayout_wrapper.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle?.onOptionsItemSelected(item)!!) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

        mAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = mAuth?.currentUser

        Log.e("error", currentUser.toString())
        if (currentUser == null) {
            startActivity(
                Intent(
                    this,
                    AuthActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
            finish()
        }
    }
}