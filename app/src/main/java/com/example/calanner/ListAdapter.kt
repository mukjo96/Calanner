package com.example.calanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import navigation.DailyFragment.Companion.mDailyPlans
import navigation.WeeklyFragment.Companion.mWeeklyPlans

class ListAdapter(private val list: List<Plan>) : RecyclerView.Adapter<PlanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlanViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan: Plan = list[position]
        holder.bind(plan)
    }

    override fun getItemCount(): Int = list.size

    fun removeDailyPlan(position: Int) {
        mDailyPlans.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeWeeklyPlan(position: Int) {
        mWeeklyPlans.removeAt(position)
        notifyDataSetChanged()
    }


    }


