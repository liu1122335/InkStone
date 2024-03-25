package com.example.inkstonedemo1.model.write

class ControllerPoint {
    var x = 0f
    var y = 0f
    var width = 0f
    var alpha = 255

    constructor()
    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    operator fun set(x: Float, y: Float, w: Float) {
        this.x = x
        this.y = y
        width = w
    }

    fun set(point: ControllerPoint) {
        x = point.x
        y = point.y
        width = point.width
    }

    override fun toString(): String {
        return "X = $x; Y = $y; W = $width"
    }
}