package com.example.rexrootadminapp

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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobReqViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.jobreq_card,parent,false)
        return JobReqViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JobReqViewHolder, position: Int) {
        holder.jobRole.text = jobReqList[position].jobRole
        holder.compName.text = jobReqList[position].companyName
        holder.pricePerClosure.text = jobReqList[position].pricePerClosure
        //holder.jobDesc.text = jobReqList[position].jobDesc
    }

    override fun getItemCount() : Int {
        return jobReqList.size
    }

}