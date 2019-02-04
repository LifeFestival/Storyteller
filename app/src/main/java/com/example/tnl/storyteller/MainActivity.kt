package com.example.tnl.storyteller

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.element_layput.*

class MainActivity : AppCompatActivity() {

    val massive: List<String> = listOf("0", "1/2", "1", "2", "3", "5", "8", "13")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myGridView.adapter = CustomAdapter(this, massive)
    }
}
