package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class classes : AppCompatActivity() {
    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<Class_details>
    private lateinit var tempArrayList : ArrayList<Class_details>
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var news : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)
        imageId = arrayOf(
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original,
            R.drawable.original
        )

        heading = arrayOf(
            "CS-A",
            "CS-B",
            "IT-A",
            "IT-B",
            "ETC-A",
            "ETC-B",
            "MECH-A",
            "MECH-B",
            "CIV-A",
            "CIV-B"
        )



        newRecylerview =findViewById(R.id.recyclerView1)
        newRecylerview.layoutManager = LinearLayoutManager(this)
        newRecylerview.setHasFixedSize(true)


        newArrayList = arrayListOf<Class_details>()
        tempArrayList = arrayListOf<Class_details>()
        getUserdata()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArrayList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){

                    newArrayList.forEach {

                        if (it.heading.toLowerCase(Locale.getDefault()).contains(searchText)){


                            tempArrayList.add(it)

                        }

                    }

                    newRecylerview.adapter!!.notifyDataSetChanged()

                }else{

                    tempArrayList.clear()
                    tempArrayList.addAll(newArrayList)
                    newRecylerview.adapter!!.notifyDataSetChanged()

                }


                return false

            }


        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getUserdata() {

        for(i in imageId.indices){

            val news = Class_details(imageId[i],heading[i])
            newArrayList.add(news)

        }

        tempArrayList.addAll(newArrayList)


        val adapter = Adapter(tempArrayList)
        val swipegesture = object : SwipeGesture(this) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val from_pos = viewHolder.absoluteAdapterPosition
                val to_pos = target.absoluteAdapterPosition

                Collections.swap(newArrayList, from_pos, to_pos)
                adapter.notifyItemMoved(from_pos, to_pos)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {

                    ItemTouchHelper.LEFT -> {

                        adapter.deleteItem(viewHolder.absoluteAdapterPosition)
                    }

                    ItemTouchHelper.RIGHT -> {

                        val archiveItem = newArrayList[viewHolder.absoluteAdapterPosition]
                        adapter.deleteItem(viewHolder.absoluteAdapterPosition)
                        adapter.addItem(newArrayList.size, archiveItem)

                    }

                }

            }

        }
        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(newRecylerview)
        newRecylerview.adapter = adapter
        adapter.setOnItemClickListener(object : Adapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@classes,"You Clicked on item no. $position",Toast.LENGTH_SHORT).show()


            var username=0
            val Intent = Intent(this@classes, MainActivity2::class.java)
           Intent.putExtra("classy", heading[position])
            startActivity(Intent)

            }


        })

    }
}
