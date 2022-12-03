package com.example.vasilyuk

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "contactdb"
        const val TABLE_NAME = "contacts"
        const val KEY_ID = "id"
        const val KEY_FIRST = "firstName"
        const val KEY_LAST = "lastName"
        const val KEY_DATE = "birthDate"
        const val KEY_NUMBER = "phoneNumber"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_NAME (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_FIRST TEXT NOT NULL,
                $KEY_LAST TEXT NOT NULL,
                $KEY_DATE TEXT NOT NULL,
                $KEY_NUMBER TEXT NOT NULL
            )""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAll(): List<Contact> {
        val result = mutableListOf<Contact>()
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, null, null,
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val firstNameIndex: Int = cursor.getColumnIndex(KEY_FIRST)
            val lastNameIndex: Int = cursor.getColumnIndex(KEY_LAST)
            val birthDateIndex: Int = cursor.getColumnIndex(KEY_DATE)
            val phoneNumber: Int = cursor.getColumnIndex(KEY_NUMBER)

            do {
                val contact = Contact(
                    cursor.getLong(idIndex),
                    cursor.getString(firstNameIndex),
                    cursor.getString(lastNameIndex),
                    cursor.getString(birthDateIndex),
                    cursor.getString(phoneNumber),

                )
                result.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return result
    }

    fun add(contact: Contact): Long {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FIRST, contact.firstName)
        contentValues.put(KEY_LAST, contact.lastName)
        contentValues.put(KEY_DATE, contact.birthDate)
        contentValues.put(KEY_NUMBER, contact.phoneNumber)
        val id = database.insert(TABLE_NAME, null, contentValues)
        close()
        return id
    }

    fun update(id: Long, contact: Contact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_FIRST, contact.firstName)
        contentValues.put(KEY_LAST, contact.lastName)
        contentValues.put(KEY_DATE, contact.birthDate)
        contentValues.put(KEY_NUMBER, contact.phoneNumber)
        database.update(TABLE_NAME, contentValues, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

    fun remove(id: Long) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

}