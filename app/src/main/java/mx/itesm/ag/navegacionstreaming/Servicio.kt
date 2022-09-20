package mx.itesm.ag.navegacionstreaming

import com.google.gson.annotations.SerializedName

// Una clase con solo variables de instancia (Datos)

data class Servicio(
    @SerializedName("servicio")
    var nombre: String
    ) {
    override fun toString(): String {
        return nombre
    }
}
