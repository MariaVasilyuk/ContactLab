package com.example.vasilyuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class InfoActivity : AppCompatActivity() {
    private val dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Информация о контакте"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val firstNameEditText = findViewById<EditText>(R.id.TextName)
        val lastNameEditText = findViewById<EditText>(R.id.TextSurname)
        val birthDateEditText = findViewById<EditText>(R.id.TextBirth)
        val phoneNumberEditText = findViewById<EditText>(R.id.TextNumber)

        val id = intent.getLongExtra(MainActivity.EXTRA_KEY, 0)
        val contact = dbHelper.getById(id)
        firstNameEditText.setText(contact?.firstName)
        lastNameEditText.setText(contact?.lastName)
        birthDateEditText.setText(contact?.birthDate)
        phoneNumberEditText.setText(contact?.phoneNumber)

        val buttonESC = findViewById<Button>(R.id.back)
        buttonESC.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonRED = findViewById<Button>(R.id.red)
        buttonRED.setOnClickListener {

            val intent = Intent(this, InfoActivity::class.java)
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