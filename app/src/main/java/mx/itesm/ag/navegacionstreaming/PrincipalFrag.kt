package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.itesm.ag.navegacionstreaming.databinding.FragmentPrincipalBinding

class PrincipalFrag : Fragment() {

    private lateinit var binding: FragmentPrincipalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrincipalBinding.inflate(layoutInflater)
        return binding.root
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
    }

}