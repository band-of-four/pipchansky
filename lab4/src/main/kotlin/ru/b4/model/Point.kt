package ru.b4.model

class Point(var x: Double, var y: Double, var hit: Boolean){
    override fun toString(): String {
        return "X: "+x+" Y: "+y+" HIT: "+hit
    }
}
