package mx.itesm.ag.navegacionstreaming

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Sergio Gonzalez
 * Define los servicios que vamos a consumir
 */

interface ServicioListaAPI {

    @GET("servicios.php")
    fun descargarListaServicios(): Call<List<Servicio>>

    @GET("{servicio}.php")
    fun descargarCosto(@Path("servicio") servicio: String): Call<CostoServicio>


}