package com.example.demo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    var setTime : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            //Toast.makeText(this, "You clicked me.", Toast.LENGTH_LONG).show()
        }

        timePicker.setOnClickListener { view ->
            clickTimePicker(view)
        }

        calculate.setOnClickListener {
            if (setTime == "" || setTime.length < 11)
                Toast.makeText(this@MainActivity, "Please Select Date and Time",Toast.LENGTH_LONG).show()
            else{
                val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH)
                val setTIME = sdf.parse(setTime)
                val milliseconds = abs(Date().time - setTIME.time)
                val seconds = milliseconds/1000
                val minutes = seconds/60
                val hours = minutes/60
                val days = hours/24
                val months = days/30
                val years = days/365
                ageInSeconds.setText(seconds.toString())
                ageinMinutes.setText(minutes.toString())
                ageinHours.setText(hours.toString())
                ageInDays.setText(days.toString())
                ageInMonths.setText(months.toString())
                ageInYears.setText(years.toString())

            }
        }


    }

    fun  clickDatePicker(view:View){
        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                View, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDATE = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            selectedDate.setText(selectedDATE)
            setTime = selectedDATE
//
        }
            ,year
            ,month
            ,day).show()
    }
    
    fun clickTimePicker(view:View) {
        val myTimer = Calendar.getInstance()
        val hours = myTimer.get(Calendar.HOUR)
        val minutes = myTimer.get(Calendar.MINUTE)


        TimePickerDialog(this,TimePickerDialog.OnTimeSetListener {
                view, selectedHours, selectedMinutes ->

            if (setTime == null)
                Toast.makeText(this@MainActivity, "Please Select Date First",Toast.LENGTH_LONG).show()
            else{
                val selectedTIME = " $selectedHours:$selectedMinutes:00"
                selectedTime.setText(selectedTIME)
                setTime += selectedTIME
            }


        },hours,minutes,false).show()


    }

}



