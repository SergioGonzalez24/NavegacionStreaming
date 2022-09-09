package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.ag.navegacionstreaming.databinding.FragmentCostoBinding
import android.widget.Toast

class CostoFrag : Fragment() {

    /**
     * @author Gilberto André García Gaytán
     * Este script descarga los datos y servicios a mostrar en la app
     */

    private val viewModel: CostoViewModel by viewModels()
    private val args: CostoFragArgs by navArgs()
    private lateinit var binding: FragmentCostoBinding
    //Costo variable
    private  var costo = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCostoBinding.inflate(layoutInflater)
        return binding.root
    }

    /*Cuando la GUI ya es visible en la pantalla (pero los componentes
 aún no pueden interactuar con el usuario)*/
    override fun onStart() {
        super.onStart()
        println("Servicio seleccionado: ${args.tipoServicio}")
        registrarObservadores()
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnCancelar.setOnClickListener {
            costo = 0.0
            regresar()
        }
        binding.btnConfirmar.setOnClickListener {
            regresar()
        }
    }

    private fun regresar() {
        setFragmentResult("calcularCosto", bundleOf("costo" to costo))
        findNavController().navigateUp()
    }

    private fun registrarObservadores() {
        viewModel.descargarCosto(args.tipoServicio)
        viewModel.costo.observe(viewLifecycleOwner){ costo ->
            this.costo = costo
            binding.tvCosto.setText("El costo de ${args.tipoServicio} es $" + "%.2f".format(costo))
        }
        viewModel.descargarCosto(args.tipoServicio)
    }

}