package mx.itesm.ag.navegacionstreaming

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

class CostoFrag : Fragment() {

    private val viewModel: CostoViewModel by viewModels()
    //argumentos
    private val args: CostoFragArgs by navArgs()
    //binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_costo, container, false)
    }
    //Pantalla ya es visible, pero la gui no es interactiva todavía
    override fun onStart() {
        super.onStart()
        println("${args.tipoServicio}")
    }

    override fun onResume() {
        super.onResume()
    }


}