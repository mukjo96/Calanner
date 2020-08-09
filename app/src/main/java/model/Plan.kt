package model

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class Plan(

    @PrimaryKey
    var id: Long = 0,
    var title: String = "",
    var contents: String = "",
    var myYear: Int = 0,
    var myMonth: Int = 0,
    var myDay: Int = 0,
    var myHour: Int = 0,
    var myMinute: Int = 0,
    var dayorweek: String = "", // 1 : daily, 2 : weekly
    var date: Int = 0,
    var userid: String = ""

): RealmObject()






