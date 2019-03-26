package com.example.tnl.storyteller

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private val THEME = "currentTheme"
    private var colorPref: SharedPreferences? = null
    private var currentTheme: Int = 0
    private var themePref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        colorPref = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE)
        themePref = getSharedPreferences(THEME, Context.MODE_PRIVATE)
        currentTheme = themePref!!.getInt(THEME, 0)
        setTheme(colorPref!!.getInt(MY_SETTINGS, 0))

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

        builder.setSingleChoiceItems(list, currentTheme) { _, which ->
            val result = when (which) {
                0 -> Pair(R.style.AppTheme, 0)
                1 -> Pair(R.style.GreenTheme, 1)
                2 -> Pair(R.style.BlueTheme, 2)
                3 -> Pair(R.style.RedTheme, 3)
                4 -> Pair(R.style.OrangeTheme, 4)
                5 -> Pair(R.style.BlackTheme, 5)

                else -> Pair(R.style.AppTheme, 0)

            }
            colorPref!!.edit().putInt(MY_SETTINGS, result.first).apply()
            themePref!!.edit().putInt(THEME, result.second).apply()
        }
            .setTitle(getString(R.string.dialog_title_string))
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
