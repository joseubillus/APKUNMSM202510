package com.example.apkunmsm

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.util.FrutaDetector

class MnDFruta : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textResultado: TextView
    private lateinit var detector: FrutaDetector
    private val PICK_IMAGE_REQUEST = 1  // Código para identificar el intent de galería

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mn_dfruta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageView = findViewById(R.id.FrmDFruta_img)
        textResultado = findViewById(R.id.FrmDFruta_lblres)
        var btnSeleccionar: Button = findViewById(R.id.FrmDFruta_btnSImg)

        detector = FrutaDetector(this)  // Instancia de tu modelo

        btnSeleccionar.setOnClickListener { abrirGaleria() }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val uri: Uri? = data.data
            if (uri != null) {
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)

                val resultado = detector.detectar(bitmap)
                textResultado.text = "Fruta detectada: $resultado"
            }
        }
    }
}