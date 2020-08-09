package com.example.calanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import model.Plan

class ListAdapter(private val realmResult: OrderedRealmCollection<Plan>,
                  private val click: (Long) -> Unit) : RecyclerView.Adapter<PlanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlanViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = realmResult[position]
        holder.bind(plan)
        holder.itemView.setOnClickListener { click(plan.id) }
    }

    override fun getItemCount(): Int = realmResult.size


    }


