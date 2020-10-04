@file:Suppress("UNREACHABLE_CODE")

package com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.e11evenyarid.R
import com.example.e11evenyarid.adapters.CategoriesAdapter
import com.example.e11evenyarid.adapters.RestApiRCVA
import com.example.e11evenyarid.models.ForRest
import kotlinx.android.synthetic.main.fragment_home__home_drawer.*
import kotlinx.android.synthetic.main.fragment_home__home_drawer.view.*
import org.json.JSONArray
import java.util.*

class Home_HomeDrawerFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home__home_drawer, container, false)
        val credentials = "605583ded615124b13e54f0472ad9da7b699d800"
        val ROOT_URL = "http://192.168.0.111:8000/utamuapi/newsposts/"
        val REGISTER = "http://192.168.43.87/Android/v1/user.php"
        activity!!.setActionBar(toolbar as Toolbar?)


        val imageList = intArrayOf(
            R.drawable.username,
            R.drawable.username,
            R.drawable.username,
            R.drawable.username,
            R.drawable.username,
            R.drawable.username,
        )

        val layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        val nameList = arrayOf("syd", "edge", "java", "major", "edge", "java", "major")
        val categoriesAdapter =
            activity?.let { CategoriesAdapter(it, imageList, nameList) }
        view.itemDisplayRCV.adapter = categoriesAdapter
        view.itemDisplayRCV.layoutManager = layoutManager


        val forRestArrayList: ArrayList<ForRest> = ArrayList<ForRest>()
        val requestQueue = Volley.newRequestQueue(activity)
        // Make a volley custom json object request with basic authentication
        val request = CustomJsonObjectRequestBasicAuth(Request.Method.GET, ROOT_URL, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val title =
                            jsonObject.getString("post_title") as String
                        val body =
                            jsonObject.getString("post_body") as String
                        val datepost =
                            jsonObject.getString("posting_date") as String
                        //traversing the json tree for the name
                        val author2 =
                            jsonObject.getJSONObject("poster_name")
                        val author1 = author2.getJSONObject("StudentId")
                        val author = author1.getString("firstname")


                        forRestArrayList.add(ForRest(title, body, datepost, author))

                        val restApiRCVA =
                            activity?.let { it1 -> RestApiRCVA(it1, forRestArrayList) }
                        val schoolslayoutManager =
                            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                        view.profName.adapter = restApiRCVA
                        view.profName.layoutManager = schoolslayoutManager

                    }
//                    Toast.makeText(activity, "response.length()", Toast.LENGTH_SHORT)
//                        .show()

                } catch (e: Exception) {
                    Toast.makeText(activity, "error ocurred $e", Toast.LENGTH_SHORT)
                        .show()
                    println(e)

                }
            }, {
                Toast.makeText(activity, "error ocurred ", Toast.LENGTH_SHORT)
                    .show()
                println("e")
            }, credentials
        )

        requestQueue.add(request)
        return view


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

// Class to make a volley json object request with basic authentication
class CustomJsonObjectRequestBasicAuth(
    method: Int, url: String,
    jsonArray: JSONArray?,
    listener: Response.Listener<JSONArray>,
    errorListener: Response.ErrorListener,
    credential_token: String
) : JsonArrayRequest(method, url, jsonArray, listener, errorListener) {

    private var mCredentials: String = credential_token

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Token $mCredentials"
        return headers
    }
}