package com.example.vasilyuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class ContactActivity : AppCompatActivity() {
    private val dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perexod)

        val id = intent.getLongExtra("id", 0)

        val buttonESC = findViewById<Button>(R.id.back)
        buttonESC.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonRED = findViewById<Button>(R.id.red)
        buttonRED.setOnClickListener {

            val intent = Intent(this, InfoAct::class.java)
            startActivity(intent)
        }
        val buttonDelete = findViewById<Button>(R.id.del)
        buttonDelete.setOnClickListener {

            dbHelper.remove(id)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}