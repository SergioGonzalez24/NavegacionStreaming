package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CostoViewModel : ViewModel()
{
    val streaming = ServicioStreaming()
    val costo = MutableLiveData<Double>()

    //interfaz
    fun calcularCosto(tipoServicio: String) {
        val costoServicio = streaming.calcularCosto(tipoServicio)
        costo.value = costoServicio // Avisa a los suscriptores
    }
}