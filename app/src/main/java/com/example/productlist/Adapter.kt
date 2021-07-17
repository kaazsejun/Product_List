package com.example.productlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.productslayout.view.*


class Adapter(private val enums:Collection<Beer>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return enums.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.productslayout,parent ,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val beer = enums.elementAt(position)
        holder.view.nameView?.text = beer.name
        holder.view.tagline.text = beer.tagline
        Glide.with(holder.view.imageView.context).load(beer.image_url).into(holder.view.imageView);
        holder.beer = beer
    }

}
class CustomViewHolder(val view: View, var beer: Beer? = null): RecyclerView.ViewHolder(view){
    init {
        view.setOnClickListener{
            val intent=Intent(view.context,DetailsActivity::class.java)
            intent.putExtra("name",beer?.name)
            intent.putExtra("image_url",beer?.image_url)
            intent.putExtra("description",beer?.description)
            intent.putExtra("brew_tips",beer?.brewers_tips)
            view.context.startActivity(intent)
        }
    }
}


