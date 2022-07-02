package com.app.amigo.Adapters

data class Spot(
    val id: Long = counter++,
    val name: String,
    val city: String,
    val url: String,
    val img : Int,
) {
    companion object {
        private var counter = 0L
    }


}
