package com.example.apkunmsm.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.apkunmsm.R
import com.example.util.Mensaje
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmeTabChat.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmeTabChat : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var lst: ListView
    private var Data:FirebaseDatabase ?= null
    private var myrRef: DatabaseReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_fragme_tab_chat, container, false)

        lst = root.findViewById(R.id.FrmChat_Lista)
        var adp = ArrayAdapter<String>(root.context,android.R.layout.simple_list_item_1)

        Data = FirebaseDatabase.getInstance()
        myrRef = Data!!.getReference("Prueba")
        myrRef!!.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                adp.clear()
                for (post:DataSnapshot  in snapshot.children){
                    var dato:String = post.getValue().toString()
                    adp.add(dato)
                    lst.adapter = adp
                }
            }
            override fun onCancelled(error: DatabaseError) {
                var ms = Mensaje(root.context)
                ms.getMensaje("Error","Error:"+error,0).show()
            }
        })
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmeTabChat.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmeTabChat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}