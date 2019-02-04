package com.example.tnl.storyteller

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.skydoves.colorpickerpreference.ColorListener
import com.skydoves.colorpickerpreference.ColorPickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.element_layput.*

class MainActivity : AppCompatActivity() {

    var color: Int? = null
    val massive: List<String> = listOf("0", "1/2", "1", "2", "3", "5", "8", "13")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myGridView.adapter = CustomAdapter(this, massive)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.color_pick_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.color_picker)
            showColorDialog()
        return true
    }

    private fun showColorDialog() {
        val builder = ColorPickerDialog.Builder(this)
        builder.setTitle("Выбор цветовой темы")
        builder.setPositiveButton("Ок", ColorListener {
            color = it.color
        })
        builder.show()
    }
}
