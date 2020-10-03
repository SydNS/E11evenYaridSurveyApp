@file:Suppress("DEPRECATION")

package com.example.e11evenyarid.authfragments

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e11evenyarid.MainActivity
import com.example.e11evenyarid.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_sign_in.*
import kotlinx.android.synthetic.main.layout_sign_in.view.*


class SignInFragment : Fragment() {

    private var txtPassword: TextInputEditText? = null
    private val txtSignUp: TextView? = null

    //    private var btnSignIn: Button? = null
    private var dialog: ProgressDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.layout_sign_in, container, false)
        if (view != null) {
            init(view)
        }
        return view
    }

    private fun init(view: View) {
        dialog = ProgressDialog(context)
        dialog!!.setCancelable(false)
        view.txtSignUp?.setOnClickListener {
            //change fragments
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frameAuthContainer, SignUpFragment()).commit()
        }
        view.btnSignIn.setOnClickListener {
            //validate fields first
            if (validate()) {
                login()
            }

            Toast.makeText(activity, "YARID", Toast.LENGTH_LONG).show()
        }
        view.txtEmailSignIn?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!txtEmailSignIn.text.toString().isEmpty()) {
                    txtLayoutEmailSignIn.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        view.txtPasswordSignIn.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (txtPasswordSignIn.text.toString().length > 7) {
                    txtLayoutPasswordSignIn.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun validate(): Boolean {
        if (txtEmailSignIn!!.text.toString().isEmpty()) {
            txtLayoutEmailSignIn!!.isErrorEnabled = true
            txtLayoutEmailSignIn!!.error = "Email is Required"
            return false
        }
        if (txtPasswordSignIn!!.text.toString().length < 8) {
            txtLayoutPasswordSignIn!!.isErrorEnabled = true
            txtLayoutPasswordSignIn!!.error = "Required at least 8 characters"
            return false
        }
        return true
    }

    private fun login() {

        val email: String = txtEmailSignIn.text.toString()
        val password: String = txtPasswordSignIn.text.toString()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    user?.email.toString()


                    Toast.makeText(
                        activity, "Welcome.${user?.email}",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(activity, MainActivity::class.java))
                    activity?.finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    val thError = task.exception?.message.toString()
                    Toast.makeText(
                        activity, "Authentication failed. $thError",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }


    }
}