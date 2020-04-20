package com.tommaso.app4store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textToFind:String
        var testo = findViewById(R.id.testo) as TextInputEditText
        var textButton = findViewById(R.id.textButton) as Button
        textButton.setOnClickListener {
            textToFind=testo.text.toString()
            Toast.makeText(this,textToFind,Toast.LENGTH_LONG).show()
        }

    }
}
