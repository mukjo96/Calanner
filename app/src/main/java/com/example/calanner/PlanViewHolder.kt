package com.example.calanner

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Plan

class PlanViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mContentsView: TextView? = null
    private var mYearView: TextView? = null
    private var mMonthView: TextView? = null
    private var mDayView: TextView? = null
    private var mHourView: TextView? = null
    private var mMinuteView: TextView? = null



    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mContentsView = itemView.findViewById(R.id.list_contents)
        mYearView = itemView.findViewById(R.id.list_year)
        mMonthView = itemView.findViewById(R.id.list_month)
        mDayView = itemView.findViewById(R.id.list_day)
        mHourView = itemView.findViewById(R.id.list_hour)
        mMinuteView = itemView.findViewById(R.id.list_minute)

    }

    fun bind(plan: Plan) {
        mTitleView?.text = plan.title
        mContentsView?.text = plan.contents
        mYearView?.text = plan.myYear.toString()+"년 "
        mMonthView?.text = plan.myMonth.toString()+"월 "
        mDayView?.text = plan.myDay.toString()+"일 "
        mHourView?.text = plan.myHour.toString()+"시 "
        mMinuteView?.text = plan.myMinute.toString()+"분"


    }

}