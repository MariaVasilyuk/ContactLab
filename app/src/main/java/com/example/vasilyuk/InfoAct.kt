package com.example.vasilyuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class InfoAct : AppCompatActivity() {
    private val dbHelper = DBHelper(this)
    private val list = mutableListOf<Contact>()
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstNameEditText = findViewById<EditText>(R.id.TextName)
        val lastNameEditText = findViewById<EditText>(R.id.TextSurname)
        val birthDateEditText = findViewById<EditText>(R.id.TextBirth)
        val phoneNumberEditText = findViewById<EditText>(R.id.TextNumber)

        val buttonESC = findViewById<Button>(R.id.buttonCancel)
        buttonESC.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val birthDate = birthDateEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()
            val contact = Contact(
                null,
                firstName,
                lastName,
                birthDate,
                phoneNumber
            )
            val id = dbHelper.add(contact)
            list.add(Contact(id, firstName, lastName, birthDate, phoneNumber))
            adapter.notifyItemInserted(list.lastIndex)
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }
}