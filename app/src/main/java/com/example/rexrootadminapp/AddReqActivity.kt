package com.example.rexrootadminapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.FirebaseDatabase

class AddReqActivity : AppCompatActivity() {
    private val ivGoBack : ImageView get() = findViewById(R.id.iv_goback)
    private val etJobRole : EditText get() = findViewById(R.id.et_jobrole)
    private val etCompName : EditText get() = findViewById(R.id.et_compname)
    private val etCompLocation : EditText get() = findViewById(R.id.et_complocation)
    private val etJobType : EditText get() = findViewById(R.id.et_jobtype)
    private val etPricePerClosure : EditText get() = findViewById(R.id.et_priceperclosure)
    private val etJobSkills : EditText get() = findViewById(R.id.et_jobskills)
    private val etJobDesc : EditText get() = findViewById(R.id.et_jobdesc)
    private val btnSubmitReg : AppCompatButton get() = findViewById(R.id.btn_submitreq)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addreq_screen)
        val firebaseDB = FirebaseDatabase.getInstance().reference

        ivGoBack.setOnClickListener {
            val intent = Intent(this@AddReqActivity,MainActivity::class.java)
            startActivity(intent)
        }

        btnSubmitReg.setOnClickListener {
            val progressDialog = ProgressDialog(this@AddReqActivity)
            progressDialog.setMessage("Adding New Requirement...")
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            val jobRole : String = etJobRole.text.toString().trim()
            val companyName : String = etCompName.text.toString().trim()
            val companyLocation : String = etCompLocation.text.toString().trim()
            val jobType : String = etJobType.text.toString().trim()
            val pricePerClosure : String = etPricePerClosure.text.toString().trim()
            val jobSkills : String = etJobSkills.text.toString().trim()
            val jobDesc : String = etJobDesc.text.toString().trim()

            val reqData = mutableMapOf(
                "jobRole" to jobRole,
                "companyName" to companyName,
                "companyLocation" to companyLocation,
                "jobType" to jobType,
                "pricePerClosure" to pricePerClosure,
                "jobSkills" to jobSkills,
                "jobDesc" to jobDesc
            )

            val reqKey = firebaseDB.push().key
            if (reqKey != null) {

                //val timestamp = -System.currentTimeMillis()
                val key = "${firebaseDB.push().key}"

                reqData["jobId"] = key

                firebaseDB.child("root").child(key).setValue(reqData) {databaseError , _->
                    progressDialog.dismiss()

                    if (databaseError != null){
                        Log.d("FirebaseDB", databaseError.message)
                        Toast.makeText(this@AddReqActivity,"Insertion Error occurred",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@AddReqActivity,"New Requirement added successfully!",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@AddReqActivity,MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

}