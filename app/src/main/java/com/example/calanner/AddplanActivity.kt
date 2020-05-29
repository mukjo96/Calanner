package com.example.calanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.fragment_add_plan.*
import navigation.DailyFragment
import navigation.WeeklyFragment
import java.util.*

class AddplanActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var cpt_button: Button
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_plan)


        textView = findViewById(R.id.textView)
        button = findViewById(R.id.btnPick)
        cpt_button = findViewById(R.id.complete_button)


        button.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@AddplanActivity, this@AddplanActivity, year, month, day)
            datePickerDialog.show()
        }

        cpt_button.setOnClickListener {
            val myPlan = Plan(title = plan_name_edittext.text.toString(), contents =  plan_txt_edittext.text.toString(),myYear =  myYear, myMonth =  myMonth, myDay =  myDay, myHour =  myHour, myMinute =  myMinute)
            if(rg_btn1.isChecked) {
                DailyFragment.mDailyPlans.add(myPlan)
            }
            else if(rg_btn2.isChecked) {
                WeeklyFragment.mWeeklyPlans.add(myPlan)
            }
            finish()
        }



        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(
                    applicationContext, "  ${radio.text} 계획을 입력합니다.",
                    Toast.LENGTH_SHORT
                ).show()

            })

        /*rd_select_btn.setOnClickListener{ //라디오 버튼 아래 버튼으로의 적용
            // Get the checked radio button id from radio group
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                Toast.makeText(applicationContext,"On button click : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            }else{
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext,"On button click : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }
        }*/
    }

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
        //textView.text =
            //"Year: " + myYear + "\n" + "Month: " + myMonth + "\n" + "Day: " + myDay + "\n" + "Hour: " + myHour + "\n" + "Minute: " + myMinute
    }

    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radioGroup.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()
    }





}
