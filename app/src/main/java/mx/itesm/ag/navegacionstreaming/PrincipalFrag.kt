package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import mx.itesm.ag.navegacionstreaming.databinding.FragmentPrincipalBinding

class PrincipalFrag : Fragment()
{

    /**
     * @author Gilberto André García Gaytán
     * Este script tiene la función principal de dar el dato dependiendo
     * que servicio escoja el usuario al dar click al boton de contratar
     */

    private val viewModel: PrincipalViewModel by viewModels()
    private lateinit var  binding: FragmentPrincipalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPrincipalBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
    }

    // Mostrar componentes
    override fun onStart() {
        super.onStart()
        configurarObservables()
        viewModel.descargarListaServicios()
    }

    private fun configurarObservables() {
        viewModel.listaServicio.observe(viewLifecycleOwner){lista ->
            val arrServicios = lista.toTypedArray()
            binding.spServicios.adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                arrServicios)
        }
    }


    private fun registrarEventos() {
        binding.btnContratar.setOnClickListener {
            val tipo = binding.spServicios.selectedItem.toString()
            val accion = PrincipalFragDirections.actionPrincipalFragToCostoFrag(tipo)
            findNavController().navigate(accion)
        }
        setFragmentResultListener("calcularCosto"){ requestKey, bundle ->
            val costo = bundle.getDouble("costo")
            binding.tvCosto.setText("Precio a pagar: $$costo")


        }
    }

}