package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PrincipalViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    // El objeto retrofit, crea el objeto que se conectará a la red
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2/moviles/") // Se utiliza 10.0.2.2 para probar localmente
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instancia que crea el objeto que realiza la descarga
    private val servicioListaAPI by lazy {
        retrofit.create(ServicioListaAPI::class.java)
    }

    // Observable
    val arrServicios = MutableLiveData<List<Servicio>>()

    //val costo = MutableLiveData<Double>()


    fun descargarListaServicios() {
        // Crea un objeto que realizará la descarga
        val call = servicioListaAPI.descargarListaServicios()

        // Inicia la descarga ASÍNCRONA
        call.enqueue(object: Callback<List<Servicio>> {
            override fun onResponse(
                call: Call<List<Servicio>>,
                response: Response<List<Servicio>>) {
                // llamada exitosa
                if(response.isSuccessful) {
                    arrServicios.value = response.body()
                    println("Arreglo descargado: ${arrServicios.value}")
                }
            }
            override fun onFailure(call: Call<List<Servicio>>, t: Throwable) {
                // llamada fallida
                println("Error en la descarga: ${t.localizedMessage}")
            }
        })
    }
}


