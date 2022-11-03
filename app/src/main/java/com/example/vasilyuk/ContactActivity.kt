package com.example.vasilyuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ContactAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perexod)
        title = "Личные данные"
        val textS = findViewById<TextView>(R.id.ttext)
        val n = intent.getStringExtra(MainActivity.EXTRA_KEY)
        textS.text = n
    }
}