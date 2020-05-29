package com.example.calanner

data class Plan(
    var title : String? = null,
    var contents : String? = null,
    var myYear : Int? = 0,
    var myMonth: Int? = 0,
    var myDay: Int? = 0,
    var myHour: Int? = 0,
    var myMinute: Int? = 0
)