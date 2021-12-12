package com.example.storingsettings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var pick_class: EditText
    lateinit var pick_num: EditText

    val PREF_DARK_THEME = "dark_theme"
    override fun onCreate(savedInstanceState: Bundle?) {

        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val useDarkTheme = SP.getBoolean(PREF_DARK_THEME, false)

        if (useDarkTheme){
            setTheme(R.style.ThemeOverlay_AppCompat_Dark)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = findViewById<Switch>(R.id.switch1)
        toggle.isChecked = useDarkTheme
        toggle.setOnCheckedChangeListener{
            view, isChecked -> toggleTheme(isChecked)
        }

        name = findViewById(R.id.edit1)
        pick_class = findViewById(R.id.edit2)
        pick_num = findViewById(R.id.edit3)
     //   switch_state = toggle

    }

    private fun toggleTheme(darkTheme: Boolean) {
        val editor = getSharedPreferences("MySharedPref", MODE_PRIVATE).edit()

        editor.apply {
            putBoolean(PREF_DARK_THEME, darkTheme)
            apply()
        }
        val intent = intent
        finish()
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()

        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        val key1 = SP.getString("name", "")
        val key2 = SP.getString("pick_class", "")
        val key3 = SP.getInt("pick_num", 0)

        name!!.setText(key1)
        pick_class!!.setText(key2)
        pick_num!!.setText(key3.toString())

    }

    override fun onPause() {
        super.onPause()

        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = SP.edit()

        myEdit.putString("name", name!!.text.toString())
        myEdit.putString("pick_class", pick_class!!.text.toString())
        myEdit.putInt("pick_num", pick_num!!.text.toString().toInt())

        myEdit.apply()

    }




}