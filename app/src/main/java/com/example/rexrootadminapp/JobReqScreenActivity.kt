package com.example.rexrootadminapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rexrootadminapp.databinding.JobreqScreenBinding
import com.google.firebase.database.FirebaseDatabase

class JobReqScreenActivity: AppCompatActivity() {

    private lateinit var binding: JobreqScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JobreqScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val firebaseDB = FirebaseDatabase.getInstance().reference

        // Get data from previous Activity
        val jobId = intent.getStringExtra("jobId").toString()
        binding.tvJobrole.text = intent.getStringExtra("jobRole")
        binding.tvCompname.text = intent.getStringExtra("compName")
        binding.tvPriceperclosure.text = intent.getStringExtra("pricePerClosure")
        binding.tvCompanylocation.text = intent.getStringExtra("compLocation")
        binding.tvJobtype.text = intent.getStringExtra("jobType")
        binding.tvJobskills.text = intent.getStringExtra("jobSkills")
        binding.tvJobdesc.text = intent.getStringExtra("jobDesc")

        binding.btnEditjobreq.setBackgroundColor(Color.parseColor("#e51e26"))
        binding.btnDeletejobreq.setBackgroundColor(Color.parseColor("#e51e26"))

        binding.ivExit.setOnClickListener {
            onBackPressed()
        }

        binding.btnEditjobreq.setOnClickListener {
            val intent = Intent(this@JobReqScreenActivity,AddReqActivity::class.java)

            intent.putExtra("jobId",jobId)
            intent.putExtra("jobRole",binding.tvJobrole.text)
            intent.putExtra("compName",binding.tvCompname.text)
            intent.putExtra("pricePerClosure",binding.tvPriceperclosure.text)
            intent.putExtra("compLocation",binding.tvCompanylocation.text)
            intent.putExtra("jobType",binding.tvJobtype.text)
            intent.putExtra("jobSkills",binding.tvJobskills.text)
            intent.putExtra("jobDesc",binding.tvJobdesc.text)
            intent.putExtra("headerName","Edit Requirement")

            startActivity(intent)
        }

        binding.btnDeletejobreq.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this@JobReqScreenActivity)

            alertDialog.setTitle("Delete Job Requirement")
            alertDialog.setMessage("Do you want to delete the job requirement?")

            alertDialog.setPositiveButton("Ok") { dialogInterface: DialogInterface, _: Int ->
                val intent = Intent(this@JobReqScreenActivity,MainActivity::class.java)
                startActivity(intent)

                firebaseDB.child("root").child(jobId).removeValue()
                dialogInterface.dismiss()
            }

            alertDialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->

                dialogInterface.dismiss()
            }

            alertDialog.show()
        }
    }
}