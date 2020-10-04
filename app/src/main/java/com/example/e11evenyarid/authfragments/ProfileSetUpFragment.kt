@file:Suppress("DEPRECATION")

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
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e11evenyarid.AuthActivity
import com.example.e11evenyarid.R
import com.example.e11evenyarid.models.UserProfile
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.layout_profile_set_up.*
import kotlinx.android.synthetic.main.layout_profile_set_up.view.*
import kotlin.concurrent.timerTask


@Suppress("DEPRECATION")
class ProfileSetUpFragment : Fragment() {
    private var dialog: ProgressDialog? = null

    private val mAuth = FirebaseAuth.getInstance()

    val database = Firebase.database
    val myRef = database.reference.child("Users")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_profile_set_up, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {

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
                createProfile()


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
        if (view.txtfirstname.text.toString().isEmpty()) {
            txtfirstLayoutname.isErrorEnabled = true
            txtfirstLayoutname.error = "Please enter your first name"
            return false
        }
        if (view.txtlastname.text.toString().isEmpty()) {
            txtLayoutlastname.isErrorEnabled = true
            txtLayoutlastname.error = "Please enter your last name"
            return false
        }

        if (view.residence.text.toString().isEmpty()) {
            txtLayoutresidence.isErrorEnabled = true
            txtLayoutresidence.error = "Please enter your residential area"
            return false
        }

        if (view.Country_of_origin.text.toString().isEmpty()) {
            txtLayout_Country_of_origin.isErrorEnabled = true
            txtLayout_Country_of_origin.error = "Please enter your Country of origin"
            return false
        }


        return true
    }

    private fun createProfile() {
        val currentuser = mAuth.currentUser
        val email: String = txtEmailSignUp.text.toString()
        val Country_of_origin: String = Country_of_origin.text.toString()
        val residence: String = residence.text.toString()
        val txtlastname: String = txtlastname.text.toString()
        val txtfirstname: String = txtfirstname.text.toString()


        FirebaseAuth.getInstance()

        var userId= currentuser?.uid
        val userProfile =
            userId?.let { UserProfile(it,email, Country_of_origin, residence, txtlastname, txtfirstname) }

        if (userId != null) {
            myRef.child(userId).setValue(userProfile)
        }

        val prefs: SharedPreferences? =
            activity?.getSharedPreferences("CheckFirstTime", Context.MODE_PRIVATE)
        prefs?.edit()?.putString("OldOrNew", email)?.apply()

        startActivity(Intent(activity, AuthActivity::class.java))
        activity?.finish()
    }


}





//.getReference("userProfiles")


//        val userProfile:HashMap<String,String> = HashMap<String,String>()
//        userProfile.put("email",email)
//        userProfile.put("Country_of_origin",Country_of_origin)
//        userProfile.put("residence",residence)
//        userProfile.put("txtlastname",txtlastname)
//        userProfile.put("txtfirstname",txtfirstname)
//        currentuser?.uid?.let { userProfile.put("userId", it) }
//
//
//        myRef.updateChildren(userProfile as Map<String, String>).addOnCompleteListener(
//            OnCompleteListener {
//                if (it.isSuccessful){
//                    Toast.makeText(activity,"bang",Toast.LENGTH_LONG).show()
//                }
//            })