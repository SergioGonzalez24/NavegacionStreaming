package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import mx.itesm.ag.navegacionstreaming.databinding.FragmentPrincipalBinding

class PrincipalFrag : Fragment() {

    private val viewModel: PrincipalViewModel by viewModels()
    private lateinit var binding: FragmentPrincipalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrincipalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        registrarObservables()
        viewModel.descargarListaServicios()
    }

    private fun registrarObservables() {
        viewModel.arrServicios.observe(viewLifecycleOwner) { listaServicios ->
            val arrServicios = listaServicios.toTypedArray()

            val adaptador = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                arrServicios
            )
            binding.spServicios.adapter = adaptador
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos() //boton contratar
    }

    private fun registrarEventos() {
        binding.btnContratar.setOnClickListener {
            val tipo = binding.spServicios.selectedItem.toString() //Servicio a contratar
            val accion = PrincipalFragDirections.actionPrincipalFragToCostoFrag(tipo)
            findNavController().navigate(accion)
         }
        setFragmentResultListener("calcularCosto") {
            requestKey, bundle ->
            val costo = bundle.getDouble("costo")
            binding.tvCosto.setText("El costo es de: $$costo")
        }
    }


}