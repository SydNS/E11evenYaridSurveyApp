package com.example.e11evenyarid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e11evenyarid.authfragments.SignInFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().replace(R.id.frameAuthContainer, SignInFragment())
            .commit()
    }
}