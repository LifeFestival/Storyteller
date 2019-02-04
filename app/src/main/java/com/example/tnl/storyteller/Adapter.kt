package com.example.tnl.storyteller

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.element_layput.view.*

class CustomAdapter : BaseAdapter{

    var massive: List<String>
    var context : Context

    constructor(context: Context, massive: List<String>): super() {
        this.context = context
        this.massive = massive
    }

    override fun getCount(): Int {
        return massive.size
    }

    override fun getItem(position: Int): Any {
        return massive[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val element = this.massive[position]
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = inflator.inflate(R.layout.element_layput, null)

        myView.elementView.setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("INTENT", element)
            context.startActivity(intent)
        }
        myView.elementView.text = element
        return myView
    }
}