package com.example.modelo

class Factura {
    private var id:Int? = 0
    private var fecha:String? = null
    private var ventas:Double? = 0.0

    constructor()
    constructor(id: Int?, fecha: String?, ventas: Double?) {
        this.id = id
        this.fecha = fecha
        this.ventas = ventas
    }


    var getId:Int? get() = id
        set(value) {id = value}
    var getFecha:String? get() = fecha
        set(value) {fecha = value}
    var getVentas:Double? get() = ventas
        set(value) {ventas = value}
}