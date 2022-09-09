package mx.itesm.ag.navegacionstreaming

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Gilberto André García Gaytán
 * Define los servicios que vamos a consumir
 */

interface ServicioListaAPI
{
    @GET("servicios.php")  //endpoint
    fun descargarListaServicios(): Call<List<Servicio>>

    @GET("{servicio}.php")
    fun descargarCosto(@Path("servicio") servicio: String): Call<CostoServicio>
}
