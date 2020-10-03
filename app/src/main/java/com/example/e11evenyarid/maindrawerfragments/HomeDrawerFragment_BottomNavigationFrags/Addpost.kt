package com.example.e11evenyarid.maindrawerfragments.HomeDrawerFragment_BottomNavigationFrags

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.e11evenyarid.R
import org.json.JSONException
import org.json.JSONObject

class Addpost : AppCompatActivity() {
    var send: Button? = null
    var postauthor: EditText? = null
    var posttitle: EditText? = null
    var postsubtitle: EditText? = null
    var postpost: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add)
        send = findViewById<View>(R.id.sendpost) as Button
        postauthor = findViewById<View>(R.id.postauthorlayout) as EditText
        posttitle = findViewById<View>(R.id.posttitlelayout) as EditText
        postsubtitle = findViewById<View>(R.id.postsubtitlelayout) as EditText
        postpost = findViewById<View>(R.id.postpostlayout) as EditText
        send!!.setOnClickListener {
            val author = postauthor!!.text.toString()
            val title = posttitle!!.text.toString()
            val subtitle = postsubtitle!!.text.toString()
            val post = postpost!!.text.toString()
            posting(author, title, subtitle, post)
        }
    }

    private fun posting(author: String, title: String, subtitle: String, post: String) {
        val parameters = JSONObject()
        try {
            parameters.put("author", author)
            parameters.put("title", title)
            parameters.put("subtitle", subtitle)
            parameters.put("post", post)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    companion object {
        private val ROOT_URL_POST =
            "http://192.168.43.87:5000/add_api_posts".replace(" ".toRegex(), "%20")
    }
}