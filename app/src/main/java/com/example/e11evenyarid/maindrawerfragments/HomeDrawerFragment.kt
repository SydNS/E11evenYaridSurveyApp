package com.example.e11evenyarid.maindrawerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.e11evenyarid.R
import com.example.e11evenyarid.authfragments.SignInFragment
import com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags.Home_HomeDrawerFragment
import com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags.Message_HomeDrawerFragment
import com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags.Notifications_HomeDrawerFragment
import com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags.Search_HomeDrawerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home_drawer.*
import kotlinx.android.synthetic.main.fragment_home_drawer.view.*


class HomeDrawerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home_drawer, container, false)
        init(view)
        return view
    }

    private fun init(view: View?) {
        fragmentManager?.beginTransaction()?.replace(R.id.fragment_home_drawer, Home_HomeDrawerFragment())
            ?.commit()
        view?.findViewById<BottomNavigationView>(R.id.bottomnavview)?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bnhome -> {
//                    Toast.makeText(activity, "addnewpost", Toast.LENGTH_LONG).show()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_home_drawer, Home_HomeDrawerFragment())
                        ?.commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bnnotifs -> {
//                    Toast.makeText(activity, "notifs", Toast.LENGTH_LONG).show()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_home_drawer, Notifications_HomeDrawerFragment())
                        ?.commit()
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.bnmessages -> {
//                    Toast.makeText(activity, "messoz", Toast.LENGTH_LONG).show()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_home_drawer, Message_HomeDrawerFragment())
                        ?.commit()
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.bnsearch -> {
//                    Toast.makeText(activity, "Search", Toast.LENGTH_LONG).show()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_home_drawer, Search_HomeDrawerFragment())
                        ?.commit()
                    return@setOnNavigationItemSelectedListener true

                }
                else -> {
                    Toast.makeText(parentFragment?.activity, "default", Toast.LENGTH_LONG).show()
                    return@setOnNavigationItemSelectedListener true

                }
            }
        }

    }




}