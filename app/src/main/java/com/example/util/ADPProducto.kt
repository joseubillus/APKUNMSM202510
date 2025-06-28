package com.example.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.example.apkunmsm.R
import com.example.modelo.Producto

class ADPProducto:BaseAdapter{
    private var BD:ArrayList<Producto> = ArrayList<Producto>()
    private lateinit var ct:Context

    constructor(c:Context) : super(){
        this.ct = c
    }

    constructor(c:Context,data:ArrayList<Producto>) : super(){
        this.ct = c
        this.BD = data
    }

    fun getAdd(obj:Producto){
        BD.add(obj)
    }

    override fun getCount(): Int {
        return BD.size
    }

    override fun getItem(position: Int): Producto {
        return BD.get(position)
    }

    override fun getItemId(position: Int): Long {
        return BD.get(position).hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var root:View? = convertView
        if(root==null){
            root = LayoutInflater.from(ct).inflate(R.layout.conf_producto,parent,false)
        }
        var id:TextView = root!!.findViewById(R.id.FrmCProd_Lblid)
        var nom:TextView = root!!.findViewById(R.id.FrmCProd_Lblnom)
        var pre:TextView = root!!.findViewById(R.id.FrmCProd_Lblpre)
        var rbar:RatingBar = root!!.findViewById(R.id.FrmCProd_Rbar)

        var pro = getItem(position)
        id.text = pro.getId
        nom.text = pro.getNom
        pre.text = pro.getPre.toString()
        rbar.rating = pro.getRan!!

        return root!!
    }
}