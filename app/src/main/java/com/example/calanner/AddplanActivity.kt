package com.example.calanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.calanner.Auth.Companion.getUid
import com.google.firebase.auth.FirebaseAuth
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_add_plan.*
import model.Plan
import java.util.*

class AddplanActivity : AppCompatActivity() {
    lateinit var dtextView: TextView
    lateinit var ttextView: TextView
    lateinit var button: Button
    lateinit var cpt_button: Button
    lateinit var radio: RadioButton
    private val realm = Realm.getDefaultInstance()
    var cal = Calendar.getInstance()

    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    var auth : FirebaseAuth? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_plan)
        realm.beginTransaction()
        val user = auth?.currentUser?.uid
        val id = intent.getLongExtra("id", -1L)


        dtextView = findViewById(R.id.datetextView)
        ttextView = findViewById(R.id.timetextView)
        cpt_button = findViewById(R.id.complete_button)

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(
                    applicationContext, "  ${radio.text} 계획을 입력합니다.",
                    Toast.LENGTH_SHORT
                ).show()


            })


        btn_datepicker.setOnClickListener {
            showDatePicker();
        }
        btn_timepicker.setOnClickListener {
            showTimePicker();
        }
        cpt_button.setOnClickListener {


            val myPlan = realm.createObject<Plan>(nextId())
            myPlan.title = plan_name_edittext.text.toString()
            myPlan.contents = plan_txt_edittext.text.toString()
            myPlan.myYear = myYear
            myPlan.myMonth = myMonth
            myPlan.myDay = myDay
            myPlan.myHour = myHour
            myPlan.myMinute = myMinute
            myPlan.date = ("$myYear"+"$myMonth"+"$myDay"+"$myHour"+"$myMinute").toInt()
            myPlan.dayorweek = "daily"
            myPlan.userid = getUid()

            if (rg_btn1.isChecked) {
                myPlan.dayorweek = "daily"

            } else if (rg_btn2.isChecked) {
                myPlan.dayorweek = "weekly"
            }
            realm.commitTransaction()

            finish()
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun showDatePicker() {
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            myYear = year
            myMonth = month + 1
            myDay = day
            dtextView.text =
                "" + myYear + "년 " + myMonth + "월 " + myDay + "일\t"
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();

    }

    fun showTimePicker() {
        TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            myHour = hour
            myMinute = minute
            ttextView.text =
                "" + myHour + "시 " + myMinute + "분"
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()


    }



    private fun nextId(): Int {
        val maxId = realm.where<Plan>().max("id")
        if(maxId != null) {
            return maxId.toInt() + 1
        }
        return 0
    }


    /*
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            this@AddplanActivity, this@AddplanActivity, hour, minute,
            DateFormat.is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        textView.text =
            "Year: " + myYear + "\n" + "Month: " + myMonth + "\n" + "Day: " + myDay + "\n" + "Hour: " + myHour + "\n" + "Minute: " + myMinute
    }
    */


    fun radio_button_click(view: View) {
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
        Toast.makeText(
            applicationContext, "On click : ${radio.text}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        realm.commitTransaction()
        finish() //액티비티 종료
    }
}

