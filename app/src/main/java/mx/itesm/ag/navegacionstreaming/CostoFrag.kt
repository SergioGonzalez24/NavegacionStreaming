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

    /**
     * @author Sergio Gonzalez
     * Este script descarga los datos y servicios a mostrar en la app
     */

class CostoFrag : Fragment() {


    private val viewModel: CostoViewModel by viewModels()
    //argumentos
    private val args: CostoFragArgs by navArgs()
    //binding
    private lateinit var binding: FragmentCostoBinding
    //Costo
    private var costo : Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCostoBinding.inflate(layoutInflater)
        return binding.root
    }
    //Pantalla ya es visible, pero la gui no es interactiva todavía
    override fun onStart() {
        super.onStart()
        println("${args.tipoServicio}")
        registrarEventos()
        configurarObservadores()
        viewModel.descargarCosto(args.tipoServicio)
    }

    private fun registrarEventos() {
        // Cancelar u Confirmar
        binding.btnCancelar.setOnClickListener {
            costo = 0.0
            setFragmentResult("calcularCosto", bundleOf("costo" to costo))
            findNavController().navigateUp()
        }
        binding.btnConfirmar.setOnClickListener {
            setFragmentResult("calcularCosto", bundleOf("costo" to costo))
            findNavController().navigateUp()
        }

    }

    private fun configurarObservadores() {
        viewModel.costo.observe(viewLifecycleOwner){ costo ->
            //manda donde esta viniendo el viewlifecycleowner
            this.costo = costo.toDouble()
            binding.tvCosto.setText("El costo del tipo de ${args.tipoServicio} es $" + "%.2f".format(costo))
        }

    }

    override fun onResume() {
        super.onResume()
    }




    }