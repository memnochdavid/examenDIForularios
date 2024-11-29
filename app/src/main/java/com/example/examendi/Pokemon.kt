package com.example.examendi

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Pokemon(
    var nombre: String = "",
    var entrenador: String = "",
    var estatura: Double=0.0,
    var tipo: String = "",
    var fechaCaptura: String = ""
) : Parcelable {
    override fun toString(): String {
        return "$nombre - $entrenador - $estatura - $tipo - $fechaCaptura"
    }
}