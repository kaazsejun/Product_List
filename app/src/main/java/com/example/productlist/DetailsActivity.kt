package com.example.productlist

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.details_layout.*
import kotlinx.android.synthetic.main.productslayout.*
import kotlinx.android.synthetic.main.productslayout.view.*

class DetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)

        val beerName= intent.getStringExtra("name")
        val beerImageUrl= intent.getStringExtra("image_url")
        val beerDescription= intent.getStringExtra("description")
        val beerBrewTips= intent.getStringExtra("brew_tips")
        supportActionBar?.title = beerName
        Glide.with(beerImageView.context).load(beerImageUrl).into(beerImageView);
        descriptionView?.text = beerDescription
        tipsView?.text=beerBrewTips
    }
}
