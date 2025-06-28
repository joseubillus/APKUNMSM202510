package com.example.modelo

class Producto {
    private var id:String? = null
    private var nom:String? = null
    private var pre:Double? = 0.0
    private var ran:Float? = null

    constructor()
    constructor(id: String?, nom: String?, pre: Double?, ran: Float?) {
        this.id = id
        this.nom = nom
        this.pre = pre
        this.ran = ran
    }

    var getId:String? get() = id
        set(value) {id = value}
    var getNom:String? get() = nom
        set(value) {nom = value}
    var getPre:Double? get() = pre
        set(value) {pre = value}
    var getRan:Float? get() = ran
        set(value) {ran = value}
}