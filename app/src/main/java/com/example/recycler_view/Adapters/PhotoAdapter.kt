package com.example.recycler_view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view.Adapters.PhotoAdapter.ViewHolder
import com.example.recycler_view.R
import com.example.recycler_view.models.photo
import com.squareup.picasso.Picasso
import retrofit2.Callback


class PhotoAdapter (
    private val photoList:List<photo>,
    val context: Callback<List<photo>>,
    private val onItemClicked:(positio:Int)->Unit
    ):RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){

     class ViewHolder(
         ItemView:View,
         private  val onItemIsClicked: (position: Int) -> Unit
     ):RecyclerView.ViewHolder(ItemView),View.OnClickListener{
         val imgview=ItemView.findViewById<ImageView>(R.id.imageview)
         val textTitle=ItemView.findViewById<TextView>(R.id.textView)
         val texturl=ItemView.findViewById<TextView>(R.id.textView)

         init{
             itemView.setOnClickListener(this)
         }
         override fun onClick(p0:View){
             val position=adapterPosition
             onItemIsClicked(position)
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view= LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_view_design,parent,false)
        return  ViewHolder(view,onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val itemViewModel= photoList[position]
        Picasso.get()
            .load(itemViewModel.thumbnailurl)
            .into(holder.imgview)
        holder.textTitle.text=itemViewModel.title
        holder.texturl.text=itemViewModel.url

    }

    override fun getItemCount(): Int {
       return photoList.size
    }
    }
