package com.example.apkunmsm.menu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmePadre: FragmentStateAdapter {
    private var num:Int = 0

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle,
                numTab:Int) : super(fragmentManager,lifecycle){
                    this.num = numTab
                }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->FragmeTabInicio()
            1-> FragmeTabProd()
            2-> FragmeTabChat()
            else->null!!
        }
    }

    override fun getItemCount(): Int = num

}