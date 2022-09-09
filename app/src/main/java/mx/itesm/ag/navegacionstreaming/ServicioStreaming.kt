package mx.itesm.ag.navegacionstreaming


/**
 * @author Gilberto André García Gaytán
 * Este script es para calcular los costos
 */

class ServicioStreaming {
        fun calcularCosto(tipo: String): Double {
            return when(tipo) {
                "Netflix" -> 190.0
                "Disney+" -> 160.0
                "HBO Max" -> 145.0
                else -> 0.0
            }
        }
}