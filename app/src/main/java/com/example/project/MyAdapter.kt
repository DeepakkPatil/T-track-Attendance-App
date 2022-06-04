package com.example.project

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val userList : ArrayList<attendance>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

//        holder.cname.text = currentitem.cname

        holder.email.text = currentitem.regEmail

        holder.mobile.text = currentitem.rollnostud
        holder.name.text = currentitem.userName
        holder.count.text=currentitem.count

//        holder.needs.text = currentitem.
//        holder.money.text = currentitem.count



// get reference to button


    }

    override fun getItemCount(): Int {

        return userList.size
    }



    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //        val cname : TextView = itemView.findViewById(R.id.cname)
        val name : TextView = itemView.findViewById(R.id.tvfirstName)
        val mobile : TextView = itemView.findViewById(R.id.mobile)
        val email : TextView = itemView.findViewById(R.id.emailid)
        val count :TextView=itemView.findViewById(R.id.cnt)
//        val needs : TextView = itemView.findViewById(R.id.needs)
//        val money : TextView = itemView.findViewById(R.id.money)




    }


}