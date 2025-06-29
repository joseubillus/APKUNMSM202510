package com.example.controlador

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.modelo.Factura
import com.example.modelo.Producto
import com.example.util.ADPProducto
import com.example.util.Mensaje
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class DFactura(var c:Context) {
    private var asyn: AsyncHttpClient = AsyncHttpClient()
    private var url:String = "http://192.168.0.14:8080/prediccion/"
    private var array: ArrayList<Factura> = ArrayList<Factura>()
    lateinit var lst: ListView
    private var ms: Mensaje = Mensaje(c)
    var adp:ArrayAdapter<String> = ArrayAdapter<String>(c,android.R.layout.simple_list_item_1)


    fun getPredictivo(_a:String,_m:String){
        url = "http://192.168.0.14:8080/prediccion/"+_a+"/"+_m
        adp.clear()
        array.clear()

        asyn.get(url,null,object: AsyncHttpResponseHandler(){
            override fun onStart() {
                super.onStart()
                ms.MProgressBarDato()
            }
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?,responseBody: ByteArray?) {
                ms.MCloseProgBar(true)
                var resp:String = java.lang.String(responseBody).toString()
                getJson(resp)
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
        var json = JSONObject(dato).getString("predicciones")
        var jsonArray = JSONArray(json)
        for (i in 0..jsonArray.length()-1){
            var id = i
            var fecha = jsonArray.getJSONObject(i).getString("fecha");
            var venta: Double = jsonArray.getJSONObject(i).getString("prediccion_ventas").toDouble();
            var fac: Factura = Factura(id,fecha,venta)

            adp.add("Fecha:"+fecha+" Total:"+venta)
            array.add(fac)
        }
    }

    fun getMen(men:String){
        var ms: AlertDialog.Builder = AlertDialog.Builder(c)
        ms.setMessage(men)
        ms.setPositiveButton("Acewptar",null)
        ms.show()
    }
}