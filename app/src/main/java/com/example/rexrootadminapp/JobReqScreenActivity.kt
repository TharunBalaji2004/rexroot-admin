package com.example.rexrootadminapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rexrootadminapp.databinding.JobreqScreenBinding

class JobReqScreenActivity: AppCompatActivity() {

    private lateinit var binding: JobreqScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JobreqScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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


    }
}