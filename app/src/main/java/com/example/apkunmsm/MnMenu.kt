package com.example.apkunmsm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.apkunmsm.menu.FragmePadre
import com.google.android.material.tabs.TabLayout

class MnMenu : AppCompatActivity() {
    private lateinit var Tabpadre: TabLayout
    private lateinit var ViewP: ViewPager2
    private lateinit var FragPadre: FragmePadre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mn_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Tabpadre = findViewById(R.id.FrmMenu_TabLayout)
        ViewP = findViewById(R.id.FrmMenu_ViewPage)
        FragPadre = FragmePadre (supportFragmentManager,
            lifecycle,Tabpadre.tabCount)
        ViewP.adapter = FragPadre

        Tabpadre.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                ViewP.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        ViewP.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Tabpadre.getTabAt(position)?.select()
            }
        })
    }
}