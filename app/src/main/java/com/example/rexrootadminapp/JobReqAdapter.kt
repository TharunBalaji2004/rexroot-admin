package com.example.rexrootadminapp

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobReqAdapter(private val jobReqList : ArrayList<JobReqDataClass>) : RecyclerView.Adapter<JobReqAdapter.JobReqViewHolder>() {

    class JobReqViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val jobRole : TextView = itemView.findViewById(R.id.tv_jobrole)
        val compName : TextView = itemView.findViewById(R.id.tv_compname)
        val pricePerClosure : TextView = itemView.findViewById(R.id.tv_priceperclosure)
        //val jobDesc : TextView = itemView.findViewById(R.id.tv_jobdesc)

        fun bind(item: JobReqDataClass) {
            jobRole.text = item.jobRole
            compName.text = item.companyName
            pricePerClosure.text = "₹" + item.pricePerClosure

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, JobReqScreenActivity::class.java)

                intent.putExtra("jobId",item.jobId)
                intent.putExtra("jobRole", item.jobRole)
                intent.putExtra("compName", item.companyName)
                intent.putExtra("compLocation", item.companyLocation)
                intent.putExtra("jobType", item.jobType)
                intent.putExtra("jobSkills", item.jobSkills)
                intent.putExtra("pricePerClosure", item.pricePerClosure)
                intent.putExtra("jobDesc", item.jobDesc)

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobReqViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.jobreq_card,parent,false)
        return JobReqViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JobReqViewHolder, position: Int) {
        val currentItem = jobReqList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() : Int {
        return jobReqList.size
    }

}