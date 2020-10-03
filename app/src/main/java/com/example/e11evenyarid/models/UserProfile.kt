package com.example.e11evenyarid.models

import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.synthetic.main.layout_profile_set_up.*

data class UserProfile (
    val email: String,
    val Country_of_origin: String,
    val residence: String,
    val txtlastname: String,
    val txtfirstname: String

)