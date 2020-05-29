package com.example.calanner

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import navigation.DailyFragment
import navigation.MonthlyFragment
import navigation.SettingsFragment
import navigation.WeeklyFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)

        bottom_navigation.selectedItemId = R.id.action_daily
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.action_daily ->{
                var dailyFragment = DailyFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,dailyFragment).commit()
                return true
            }
            R.id.action_weekly ->{
                var weeklyFragment = WeeklyFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,weeklyFragment).commit()
                return true
            }
            R.id.action_monthly ->{
                var monthlyFragment = MonthlyFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,monthlyFragment).commit()
                return true
            }
            R.id.action_settings ->{
                var settingsFragment = SettingsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,settingsFragment).commit()
                return true
            }
            /*R.id.action_add_photo ->{
                var dailyFragment = DailyFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,dailyFragment).commit()
                return true
            }*/
        }
        return false
    }

}
