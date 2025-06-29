package com.example.apkunmsm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MnLogin : AppCompatActivity() {
    private lateinit var txtusu:EditText
    private lateinit var txtpas:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mn_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtusu = findViewById(R.id.FrmLog_txtusu)
        txtpas = findViewById(R.id.FrmLog_txtpas)
    }

    fun OnClick_btnacep(v:View){
        var usu=txtusu.text.toString()
        var pas=txtpas.text.toString()
        if(usu.equals("jose") && pas.equals("123"))
            startActivity(Intent(this, MnLineChart::class.java))
        else
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
    }
}