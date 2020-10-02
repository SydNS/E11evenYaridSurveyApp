@file:Suppress("DEPRECATION")

package com.example.e11evenyarid.authfragments

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e11evenyarid.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_sign_up.*
import kotlinx.android.synthetic.main.layout_sign_up.view.*


class SignUpFragment : Fragment() {
    private var dialog: ProgressDialog? = null

    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.layout_sign_up, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        dialog = ProgressDialog(context)
        dialog!!.setCancelable(false)
        view.txtSignIn.setOnClickListener {
            //change fragments
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frameAuthContainer, SignInFragment()).commit()
        }
        view.btnSignUp.setOnClickListener {
            //validate fields first
            if (validate(view)) {
                register(view)
            }
        }

        view.txtEmailSignUp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!txtEmailSignUp.text.toString().isEmpty()) {
                    txtLayoutEmailSignUp.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        view.txtPasswordSignUp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (txtPasswordSignUp.text.toString().length > 7) {
                    txtLayoutPasswordSignUp.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        view.txtConfirmPasswordSignUp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (txtConfirmPasswordSignUp.text.toString() == txtPasswordSignUp.text.toString()
                ) {
                    txtLayoutConfrimSignUp.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })


    }

    private fun validate(view: View): Boolean {
        if (txtEmailSignUp.text.toString().isEmpty()) {
            txtLayoutEmailSignUp.isErrorEnabled = true
            txtLayoutEmailSignUp.error = "Email is Required"
            return false
        }
        if (txtPasswordSignUp.text.toString().length < 8) {
            txtLayoutPasswordSignUp.isErrorEnabled = true
            txtLayoutPasswordSignUp.error = "Required at least 8 characters"
            return false
        }
        if (txtConfirmPasswordSignUp.text.toString() != txtPasswordSignUp.text.toString()
        ) {
            txtLayoutConfrimSignUp.isErrorEnabled = true
            txtLayoutConfrimSignUp.error = "Password does not match"
            return false
        }

        return true
    }

    private fun register(view: View) {
        val email: String = txtEmailSignUp.text.toString()
        val password: String = txtPasswordSignUp.text.toString()
        var mAuth = FirebaseAuth.getInstance();
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    Toast.makeText(
                        activity, "Authentication Passed.$email",
                        Toast.LENGTH_SHORT
                    ).show()

                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.frameAuthContainer, SignInFragment()).commit()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    val thError=task.exception?.message.toString()
                    Toast.makeText(activity, "Signing up Failed. $thError",Toast.LENGTH_LONG).show()
                }
            }

    }


}