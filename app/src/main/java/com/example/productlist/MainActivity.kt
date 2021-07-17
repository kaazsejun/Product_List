package com.example.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        fetchJson()
    }

    fun fetchJson(){
        val httpclient = OkHttpClient()
        val url = "https://api.punkapi.com/v2/beers/"
        val request = Request.Builder().url(url).build()

        httpclient.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                val collectionType: Type = object : TypeToken<Collection<Beer?>?>() {}.type
                val enums:Collection<Beer> = gson.fromJson(body, collectionType)
               runOnUiThread(){
                recyclerView_main.adapter= Adapter(enums)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Fetch Error")
            }
        })

    }

}
class Beer(val id: Int,val name:String,val tagline:String,val image_url:String,val description:String,val brewers_tips:String)