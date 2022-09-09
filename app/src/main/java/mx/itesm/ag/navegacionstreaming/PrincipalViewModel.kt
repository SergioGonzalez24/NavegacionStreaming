package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import mx.itesm.ag.navegacionstreaming.CostoViewModel
import android.widget.Toast

class PrincipalViewModel : ViewModel() {

    /**
     * @author Gilberto André García Gaytán
     * Este script descarga los datos y servicios a mostrar en la app
     */

    // La red para descargar los datos
    private  val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2/moviles/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioListaAPI by lazy {
        retrofit.create(ServicioListaAPI::class.java)
    }


    //Observables
    val listaServicio = MutableLiveData<List<Servicio>>()

    //GUI
    fun descargarListaServicios() {
        val call = servicioListaAPI.descargarListaServicios()
        call.enqueue(object: Callback<List<Servicio>>{
            override fun onResponse(  //Éxito
                call: Call<List<Servicio>>,
                response: Response<List<Servicio>>
            ) {
                if (response.isSuccessful) {
                    val lista = response.body()
                    listaServicio.value = lista!!
                    println("Lista Descargada: $lista")

                }else {
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Servicio>>, t: Throwable) { // cuando hay error
                println("ERROR: ${t.localizedMessage}")
            }

        })

    }
}