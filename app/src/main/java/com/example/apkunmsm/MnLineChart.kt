package com.example.apkunmsm

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

class MnLineChart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mn_line_chart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*****************Activar el Toolbar****************/
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        /****************Activar el Toolbar****************/

        val chart = findViewById<LineChart>(R.id.chartPrediccion)
        val entries = ArrayList<Entry>()

        for (i in 0..200){
            val venta = Random.nextFloat() * (300 - 150) + 150  // Entre 150 y 300
            entries.add(Entry(i.toFloat(), venta))
        }
        /*entries.add(Entry(1f, 200f))
        entries.add(Entry(2f, 180f))
        entries.add(Entry(3f, 220f))
        entries.add(Entry(4f, 210f))*/

        val dataSet = LineDataSet(entries, "Ventas")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 3f
        dataSet.setDrawValues(true)
        dataSet.setDrawFilled(true)
        dataSet.fillColor = Color.LTGRAY

        val lineData = LineData(dataSet)
        chart.data = lineData

        //configuración del gráfico
        chart.description.text = "Ventas previstas por día"
        chart.axisRight.isEnabled = false
        chart.setTouchEnabled(true)
        chart.setPinchZoom(true)
        chart.invalidate() //refrescar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed() //retroceder o finish()
        return true
    }

}