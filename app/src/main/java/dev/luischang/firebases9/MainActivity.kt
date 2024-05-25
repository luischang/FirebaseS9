package dev.luischang.firebases9

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = FirebaseFirestore.getInstance()
        val tvCurso: TextView = findViewById(R.id.tvCurso)
        val tvNota: TextView = findViewById(R.id.tvNota)

        db.collection("courses")
            .addSnapshotListener{snapshots, e->
                if(e!=null){
                    Log.e("ERROR","Ocurrió un error")
                    return@addSnapshotListener
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED ->
                            {
                                tvCurso.text = dc.document.data["description"].toString()
                                tvNota.text= dc.document.data["score"].toString()

                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content)
                                        , "Se agregó/modificó un documento"
                                        , Snackbar.LENGTH_LONG
                                    ).show()
                            }
                        DocumentChange.Type.REMOVED ->
                        {
                            Snackbar
                                .make(
                                    findViewById(android.R.id.content)
                                    , "Se eliminó el documento"
                                    , Snackbar.LENGTH_LONG
                                ).show()
                        }
                    }
                }

            }

    }
}