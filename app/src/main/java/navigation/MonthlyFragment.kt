package navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calanner.AddplanActivity
import com.example.calanner.MainActivity
import com.example.calanner.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_monthly.view.*
import model.Plan


class MonthlyFragment : Fragment(){
    var fragmentView : View? = null
    private val realm = Realm.getDefaultInstance()
    lateinit var widget: MaterialCalendarView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = getActivity() as MainActivity
        widget = view.findViewById(R.id.calendarView) as MaterialCalendarView
        val dailyResult: RealmResults<Plan> = realm.where<Plan>()
            .equalTo("dayorweek", "daily")
            .findAll()

        val weeklyResult: RealmResults<Plan> = realm.where<Plan>()
            .equalTo("dayorweek", "weekly")
            .findAll()
        for(index in dailyResult) {
            val mydate = CalendarDay.from(index.myYear, index.myMonth-1, index.myDay) // year, month, date
            widget.addDecorators(DailyDayDecorator(activity, mydate))
        }

        for(index in weeklyResult) {
            val mydate = CalendarDay.from(index.myYear, index.myMonth-1, index.myDay) // year, month, date
            widget.addDecorators(WeeklyDayDecorator(activity, mydate))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_monthly,container,false)

        fragmentView?.monthly_add_plan_btn?.setOnClickListener {
            startActivity(Intent(activity, AddplanActivity::class.java))
        }

        return fragmentView


    }


}





