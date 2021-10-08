package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{ view->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view:View)
    {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd= DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"       //${selectedMonth+1} because january is set to 0 and so on
                tvSelectedDate.text=selectedDate

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)     //The formatter will parse the selected date in to Date object so we can simply get date in to milliseconds.
                val selectedDateToMinutes = theDate!!.time / 60000  //Millisecond to minute
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                tvSelectedDateInMinutes.text = differenceInMinutes.toString()
            },year, month, day)
        dpd.datePicker.maxDate = Date().time - 86400000      // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today and future day.
        dpd.show() // It is used to show the datePicker Dialog.
    }

}
