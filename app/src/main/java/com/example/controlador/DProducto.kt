package com.example.controlador

import android.content.Context
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.modelo.Producto
import com.example.util.ADPProducto
import com.example.util.Mensaje
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class DProducto(var c:Context) {
    private var asyn: AsyncHttpClient = AsyncHttpClient()
    private var url:String = "http://192.168.0.14:8080/productos/"
    private var array: ArrayList<Producto> = ArrayList<Producto>()
    lateinit var lst: ListView
    private var ms: Mensaje = Mensaje(c)

    fun getList(){
        asyn.get(url,null,object:AsyncHttpResponseHandler(){
            override fun onStart() {
                super.onStart()
                ms.MProgressBarDato()
            }
            override fun onSuccess(statusCode: Int,
                headers: Array<out Header>?,responseBody: ByteArray?) {
                ms.MCloseProgBar(true)
                var resp:String = java.lang.String(responseBody).toString()
                getJson(resp)
                var adp:ADPProducto = ADPProducto(c,array)
                lst.adapter = adp
            }
            override fun onFailure(
                statusCode: Int,headers: Array<out Header>?,
                responseBody: ByteArray?,error: Throwable?) {
                ms.MCloseProgBar(true)
                ms.getMensaje("Error","Error"+error,0).show()
            }
        })
    }
    fun getJson(dato:String){
        var json = JSONArray(dato)
        for (i in 0..json.length()-1){
            var id = json.getJSONObject(i).getString("id")
            var nom = json.getJSONObject(i).getString("nom")
            var pre: Double = json.getJSONObject(i).getString("pre").toDouble()
            var rank: Float = json.getJSONObject(i).getString("rang").toFloat()
            var pro: Producto = Producto(id,nom,pre,rank)
            array.add(pro)
        }
    }

    fun getMen(men:String){
        var ms: AlertDialog.Builder = AlertDialog.Builder(c)
        ms.setMessage(men)
        ms.setPositiveButton("Acewptar",null)
        ms.show()
    }
}