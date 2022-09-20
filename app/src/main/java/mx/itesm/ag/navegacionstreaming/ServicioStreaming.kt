package mx.itesm.ag.navegacionstreaming

/**
 * @author Sergio Gonzalez
 * Este script es para asignar los costos a sus servicios correspondientes
 *
 * NOTA: SOLO FUNCIONA EN CASO DE QUE NUESTRA APP NO ESTE CONECTADA A INTERNET
 */

class ServicioStreaming {
    fun calcularCosto(tipo: String): Double {
        return when(tipo) {
            "Netflix" -> 190.0
            "Disney" -> 160.0
            "HBO Max" -> 145.0
            else -> 0.0
        }
    }
}