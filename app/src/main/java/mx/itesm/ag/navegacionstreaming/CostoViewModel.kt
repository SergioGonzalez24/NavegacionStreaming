package mx.itesm.ag.navegacionstreaming

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CostoViewModel : ViewModel() {

    /**
     * @author Gilberto André García Gaytán
     * Este script va a detectar cual es la variable de costo extraída de los phps y del servidro local
     */

    //variables observables
    val costo = MutableLiveData<Double>()

    //se usa la red local para descargar los datos
    private  val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2/moviles/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioListaAPI by lazy {
        retrofit.create(ServicioListaAPI::class.java)
    }

    fun descargarCosto(tipoServicio: String) {
        val call = servicioListaAPI.descargarCosto(tipoServicio)
        call.enqueue(object: Callback<CostoServicio> {
            override fun onResponse(call: Call<CostoServicio>,
                                    response: Response<CostoServicio>) {
                costo.value = response.body()?.costo?.toDouble()
            }
            override fun onFailure(call: Call<CostoServicio>, t: Throwable) {
                println("Error al descargar costo: ${t.localizedMessage}")

            }
        })
    }

}