package com.example.rexrootadminapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private val btnAddNewData : AppCompatButton
        get() = findViewById(R.id.btn_addnewreq)
    private val loadingProgressBar : ProgressBar
        get() = findViewById(R.id.pb_jobreq)
    private val tvNoResults : TextView
        get() = findViewById(R.id.tv_noresults)
    private lateinit var recyclerView: RecyclerView
    private lateinit var jobReqList: ArrayList<JobReqDataClass>
    private lateinit var firebaseDB : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        recyclerView = findViewById(R.id.rv_jobreq)
        recyclerView.layoutManager = LinearLayoutManager(this)

        jobReqList = arrayListOf()
        loadingProgressBar.visibility = ProgressBar.VISIBLE

        firebaseDB = FirebaseDatabase.getInstance().getReference("root")

        firebaseDB.orderByKey().addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    loadingProgressBar.visibility = ProgressBar.INVISIBLE
                    tvNoResults.visibility = View.INVISIBLE
                    for (dataSnapshot in snapshot.children){
                        val jobReqCard = dataSnapshot.getValue(JobReqDataClass::class.java)
                        if (!jobReqList.contains(jobReqCard)){
                            jobReqList.add(jobReqCard!!) //null check
                        }
                    }

                } else {
                    loadingProgressBar.visibility = ProgressBar.INVISIBLE
                    tvNoResults.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FirebaseDB","Error Occured:"+error)
                Toast.makeText(this@MainActivity,"Database error occured",Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.adapter = JobReqAdapter(jobReqList)

        btnAddNewData.setOnClickListener {
            val intent = Intent(this@MainActivity,AddReqActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}