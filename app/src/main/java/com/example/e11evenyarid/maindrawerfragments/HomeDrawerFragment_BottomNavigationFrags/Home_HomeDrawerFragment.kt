@file:Suppress("UNREACHABLE_CODE")

package com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.e11evenyarid.R
import com.example.e11evenyarid.adapters.RestApiRCVA
import com.example.e11evenyarid.models.ForRest
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class Home_HomeDrawerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home__home_drawer, container, false)

        val INTERNET_PERMISSION_CODE = 1
        val ROOT_URL = "http://192.168.43.87:5000/api_posts"
        val REGISTER = "http://192.168.43.87/Android/v1/user.php"


        val forRestArrayList: ArrayList<ForRest> = ArrayList<ForRest>()
        val recyclerView = view?.findViewById<View>(R.id.profName) as RecyclerView


        val requestQueue = Volley.newRequestQueue(activity)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, ROOT_URL, null, {
                fun onResponse(response: JSONObject) {
                    try {
                        val jsonArray = response.getJSONArray("List of Posts")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val author = jsonObject.getString("author") as String
                            val title = jsonObject.getString("title") as String
                            val post = jsonObject.getString("post") as String
                            val creationDate = jsonObject.getString("creationDate") as String
                            forRestArrayList.add(ForRest(title, post, author, creationDate))
                        }
                        val restApiRCVA = context?.let { it1 -> RestApiRCVA(it1, forRestArrayList) }
                        recyclerView.adapter = restApiRCVA
                        val schoolslayoutManager =
                            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                        recyclerView.layoutManager = schoolslayoutManager
//                        restApiRCVA?.setOnRCVItemClickListener(context)
                    } catch (e: JSONException) {
//                                    e.printStackTrace();
//                                    profName.setText(""+e);
                        Toast.makeText(activity, "error ocurred$e", Toast.LENGTH_SHORT)
                            .show()
                        println(e)
                    }
                }
            }, {
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(activity, "Sorry$error", Toast.LENGTH_SHORT).show()
                }
            })
        requestQueue.add(jsonObjectRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.homedrawerfragmentmenuitem, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                val intent = Intent(activity, Addpost::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}