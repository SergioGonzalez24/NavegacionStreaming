package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CostoViewModel : ViewModel() {


    val streaming = ServicioStreaming()


    fun calcularCosto(tipoServicio: String) {
        val costoServicio = streaming.calcularCosto(tipoServicio)
        costo.value = costoServicio
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2/moviles/") // Se utiliza 10.0.2.2 para probar localmente
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Instancia que crea el objeto que realiza la descarga
    private val servicioListaAPI by lazy{
        retrofit.create(ServicioListaAPI::class.java)
    }
    // Observable
    val costo = MutableLiveData<Double>()

    fun descargarCosto(tipoServicio: String) {
        val call = servicioListaAPI.descargarCosto(tipoServicio)
        call.enqueue(object: Callback<CostoServicio> {
            override fun onResponse(call: Call<CostoServicio>, response: Response<CostoServicio>) {
                costo.value = response.body()?.costo
            }
            override fun onFailure(call: Call<CostoServicio>, t: Throwable) {
                println("Error al descargar costo: ${t.localizedMessage}")
            }
        })
    }


}