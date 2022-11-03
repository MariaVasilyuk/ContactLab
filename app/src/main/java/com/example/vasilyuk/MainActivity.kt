package com.example.vasilyuk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val dbHelper = DBHelper(this)

    companion object {
        const val EXTRA_KEY = "EXTRA"
    }

    private val list = mutableListOf<Contact>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Телефонная книга"


        list.addAll(dbHelper.getAll())

        val buttonAdd = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        adapter = RecyclerAdapter(list, {
            val intent = Intent(this, ContactAct::class.java)
            intent.putExtra(EXTRA_KEY, list[it].title)
            startActivity(intent)

        }, {
            dbHelper.remove(list[it].id)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)

        })

        buttonAdd.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                val title = editText.text.toString()
                val id = dbHelper.add(title)
                list.add(
                    Contact(
                        id,
                        title
                    )
                )
                adapter.notifyItemInserted(list.lastIndex)
                editText.setText("")
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }
}



