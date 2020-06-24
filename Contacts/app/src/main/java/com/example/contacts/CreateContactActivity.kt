package com.example.contacts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        val nameText = findViewById<EditText>(R.id.input_name)
        val numberText = findViewById<EditText>(R.id.input_number)
        val confirmButton = findViewById<Button>(R.id.confirm)
        val cancelButton = findViewById<Button>(R.id.cancel)
        confirmButton.setOnClickListener {
            if (nameText.text.isEmpty() || numberText.text.isEmpty()) {
                Toast.makeText(this, "Fill all fields to create new contact", Toast.LENGTH_SHORT)
                    .show()
            } else {
//                contactDao.add(Contact(null, nameText.text.toString(), numberText.text.toString()))
                val intent = Intent()
                intent.putExtra("name", nameText.text.toString())
                intent.putExtra("number", numberText.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        cancelButton.setOnClickListener {
            finish()
        }
    }
}