package mx.itesm.ag.navegacionstreaming

import com.google.gson.annotations.SerializedName

/**
 * @author Gilberto André García Gaytán
 * Se crea la data class para el @CostoServicio y se convierte a string
 */

data class CostoServicio(
        @SerializedName("costo")
        var costo: String
){
        override fun toString(): String {
                return costo
        }
}
