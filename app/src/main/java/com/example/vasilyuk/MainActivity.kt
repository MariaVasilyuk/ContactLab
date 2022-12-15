package com.example.vasilyuk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        adapter = RecyclerAdapter({
            println("first")
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra(EXTRA_KEY, list[it].id)
            startActivity(intent)
        }, {
            println("second")
            list[it].id?.let { it1 -> dbHelper.remove(it1) }
            list.removeAt(it)
            adapter.saveClist(list)
        })
        adapter.saveClist(list)

        editText.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch(Dispatchers.IO)
            {
                val list = list.filter {
                    it.firstName?.contains(text.toString(), true)
                            || it.lastName?.contains(text.toString(), true)
                }
                if (!list.isEmpty()) {
                    withContext(Dispatchers.Main) {
                        adapter.saveClist(list)
                    }
                } else {
                    adapter.saveClist(list)
                }
                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                }
            }
        }

        buttonAdd.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }

}



