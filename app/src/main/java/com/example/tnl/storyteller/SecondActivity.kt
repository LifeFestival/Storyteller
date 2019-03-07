package com.example.tnl.storyteller

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val MY_SETTINGS = "mySettings"
    private var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        pref = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        setTheme(pref!!.getInt(MY_SETTINGS, 0))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        finalView.text = intent.getStringExtra("INTENT")
    }
}
