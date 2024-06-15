package dev.luischang.firebases9.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.firebase.firestore.FirebaseFirestore
import dev.luischang.firebases9.R
import dev.luischang.firebases9.adapter.StadiumAdapter
import dev.luischang.firebases9.model.StadiumModel


class StadiumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_stadium, container, false)
        val rvStadium: RecyclerView = view.findViewById(R.id.rvStadium)
        var lstStadiums: List<StadiumModel>
        val db = FirebaseFirestore.getInstance()
        activity?.title = "Stadiums"

        db.collection("stadiums")
            .addSnapshotListener{snap,error->
                if(error!=null){
                    Log.e("ERROR-FIREBASE","Detalle del error: ${error.message}")
                    return@addSnapshotListener
                }
                lstStadiums = snap!!.documents.map{document->
                    StadiumModel(
                        document["name"].toString(),
                        document["image"].toString(),
                        document["capacity"].toString(),
                        document["location"].toString(),
                    )
                }
                rvStadium.adapter = StadiumAdapter(lstStadiums)
                rvStadium.layoutManager = LinearLayoutManager(requireContext())
            }
        return view
    }
}