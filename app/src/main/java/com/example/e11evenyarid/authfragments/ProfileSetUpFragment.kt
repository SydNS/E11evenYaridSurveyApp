package com.example.e11evenyarid.authfragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.e11evenyarid.MainActivity
import com.example.e11evenyarid.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_profile_set_up.*
import kotlinx.android.synthetic.main.layout_profile_set_up.view.*


@Suppress("DEPRECATION")
class ProfileSetUpFragment : Fragment() {
    private var dialog: ProgressDialog? = null

    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.layout_profile_set_up, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        val user = mAuth?.currentUser

        val preferences = this.activity!!.getSharedPreferences(
            "Stage1Details",
            Context.MODE_PRIVATE
        )
        val useremail = preferences.getString("useremail", "email")

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
                createProfile(view)


            }
//            Toast.makeText(activity, "Authentication Passed.",
//                Toast.LENGTH_SHORT).show()
        }

        view.txtEmailSignUp.hint = useremail.toString()
        view.txtEmailSignUp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!txtEmailSignUp.text.toString().isEmpty()) {
                    txtLayoutEmailSignUp.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        view.txtfirstname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (txtfirstname.text.toString().isEmpty()) {
                    txtfirstLayoutname.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        view.txtlastname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (txtlastname.text.toString().isEmpty()) {
                    txtLayoutlastname.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        view.residence.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (residence.text.toString().isEmpty()) {
                    txtLayoutresidence.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        view.Country_of_origin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (Country_of_origin.text.toString().isEmpty()) {
                    txtLayout_Country_of_origin.isErrorEnabled = false
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
        if (view?.txtfirstname.text.toString().isEmpty()) {
            txtfirstLayoutname.isErrorEnabled = true
            txtfirstLayoutname.error = "Please enter your first name"
            return false
        }
        if (view?.txtlastname.text.toString().isEmpty()) {
            txtLayoutlastname.isErrorEnabled = true
            txtLayoutlastname.error = "Please enter your last name"
            return false
        }

        if (view?.residence.text.toString().isEmpty()) {
            txtLayoutresidence.isErrorEnabled = true
            txtLayoutresidence.error = "Please enter your residential area"
            return false
        }

        if (view?.Country_of_origin.text.toString().isEmpty()) {
            txtLayout_Country_of_origin.isErrorEnabled = true
            txtLayout_Country_of_origin.error = "Please enter your Country of origin"
            return false
        }


        return true
    }

    private fun createProfile(view: View) {

        val prefs: SharedPreferences? =activity?.getSharedPreferences("CheckFirstTime", Context.MODE_PRIVATE)
        prefs?.edit()?.putString("OldOrNew","Installed_Already" )?.apply()

        startActivity(Intent(activity,MainActivity::class.java))
        activity?.finish()

//        val email: String = txtEmailSignUp.text.toString()
//        val password: String = txtPasswordSignUp.text.toString()
//        var mAuth = FirebaseAuth.getInstance();
//        mAuth?.createUserWithEmailAndPassword(email, password)
//            ?.addOnCompleteListener(activity!!) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = mAuth.currentUser
//                    Toast.makeText(
//                        activity, "Authentication Passed.$email",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    startActivity(Intent(activity, MainActivity::class.java))
//
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        activity, "Authentication failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                // ...
//            }
//
    }


}