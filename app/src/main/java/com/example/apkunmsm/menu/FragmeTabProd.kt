package com.example.apkunmsm.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.apkunmsm.R
import com.example.controlador.DFactura

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmeTabProd.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmeTabProd : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_fragme_tab_prod, container, false)

        var dfac:DFactura = DFactura(root.context)


        var a: TextView = root.findViewById(R.id.FrmPred_txta)
        var m: TextView = root.findViewById(R.id.FrmPred_txtmes)
        var lst: ListView = root.findViewById(R.id.FrmPred_Lst)
        var btn: Button = root.findViewById(R.id.FrmPred_btncon)
        btn.setOnClickListener {
            dfac.lst = lst
            dfac.getPredictivo(a.text.toString(), m.text.toString())
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmeTabProd.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmeTabProd().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}