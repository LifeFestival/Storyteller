package com.example.tnl.storyteller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.skydoves.colorpickerpreference.ColorListener
import com.skydoves.colorpickerpreference.ColorPickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val themes = mapOf(
        "Purple" to 0,
        "Green" to 1,
        "Blue" to 2,
        "Red" to 3,
        "Orange" to 4,
        "Black" to 5
    )
    private val massive: List<String> = listOf("0", "1/2", "1", "2", "3", "5", "8", "13")
    private val MY_SETTINGS = "mySettings"
    private var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        pref = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        setTheme(pref!!.getInt(MY_SETTINGS, 0))
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
            showDialog()
        return true
    }

    private fun showDialog() {
        val builder = android.app.AlertDialog.Builder(this)

        val list = themes.map { it.key }.toTypedArray()

        builder.setSingleChoiceItems(list, 0) { _, which ->
            val result = when (which) {
                0 -> R.style.AppTheme
                1 -> R.style.GreenTheme
                2 -> R.style.BlueTheme
                3 -> R.style.RedTheme
                4 -> R.style.OrangeTheme
                5 -> R.style.BlackTheme

                else -> R.style.AppTheme

            }
            pref!!.edit().putInt(MY_SETTINGS, result).apply()
        }
            .setTitle("Выбор цветовой схемы")
            .setPositiveButton("Ок") { dialog, _ ->
                dialog.cancel()
                val intent = intent
                finish()
                startActivity(intent)
            }
            .create()
            .show()
    }
}
