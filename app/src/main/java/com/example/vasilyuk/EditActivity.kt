package com.example.vasilyuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditActivity : AppCompatActivity() {

    private val dbHelper = DBHelper(this)
    private val list = mutableListOf<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Новый контакт"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val id = intent.getLongExtra(MainActivity.EXTRA_KEY, 0)
        val contact = dbHelper.getById(id)
        println(contact)
        val firstNameEditText = findViewById<EditText>(R.id.Name)
        val lastNameEditText = findViewById<EditText>(R.id.Surname)
        val birthDateEditText = findViewById<EditText>(R.id.birthDate)
        val phoneNumberEditText = findViewById<EditText>(R.id.TelNamber)


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
            //dbHelper.update(id,contact)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}