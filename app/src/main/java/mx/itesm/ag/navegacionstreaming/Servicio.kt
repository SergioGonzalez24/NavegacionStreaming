package mx.itesm.ag.navegacionstreaming

import com.google.gson.annotations.SerializedName


/**
 * @author Gilberto André García Gaytán
 * Este script es para generar los get, set, equals, etc
 */
//Una clase con solo variables de instancia (datos)
data class Servicio(
    @SerializedName("servicio")
    var nombre: String
){
    override fun toString(): String {
        return nombre
    }
}
