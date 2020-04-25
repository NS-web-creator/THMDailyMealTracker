package edu.student.THMDailyMealTracker

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //textboxes
        val menuTitle = findViewById<TextView>(R.id.menuTitle)
        val breakfastTime = findViewById<TextView>(R.id.breakfastTime)
        val lunchTime = findViewById<TextView>(R.id.lunchTime)
        val snackTime = findViewById<TextView>(R.id.snackTime)
        val dinnerTime = findViewById<TextView>(R.id.dinnerTime)
        val breakfastType = findViewById<TextView>(R.id.breakfastType)
        val lunchType = findViewById<TextView>(R.id.lunchType)
        val snackType = findViewById<TextView>(R.id.snackType)
        val dinnerType = findViewById<TextView>(R.id.dinnerType)

        //buttons
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnSave = findViewById<Button>(R.id.btnSave)

        //loads intent string from MainActivity.kt
        var name: String = intent.getStringExtra("sendName")
        var intentHour: String = intent.getStringExtra("sendHour")
        var breakfastTypeLetter: String = intent.getStringExtra("sendSpinner").substring(0,1)
        var hour = intentHour.toInt();

        //make meal type array
        var mealType = arrayOf<String>()
        if(breakfastTypeLetter == "E")
        {
            mealType = arrayOf<String>("S","FP","S")
        }
        else if(breakfastTypeLetter == "S")
        {
            mealType = arrayOf<String>("S","FP","E")
        }
        else if(breakfastTypeLetter == "F")
        {
            mealType = arrayOf<String>("E","S","S")
        }

        if(hour < 1 || hour > 12)
        {
            menuTitle.text = "Invalid Input: value must be between 1 and 12... go back and try again!"
        }
        else
        {
            //set title
            menuTitle.text = "Hello " + name + "\nHere is your THM Meal Schedule for: ${getFormattedDate()}\n\n"

            //set breakfast row
            breakfastTime.text = hour.toString()
            breakfastType.text = breakfastTypeLetter

            //set lunch row
            hour += 3
            if(hour > 12)
                hour -= 12
            lunchTime.text = hour.toString()
            lunchType.text = mealType[0]

            //set snack row
            hour += 3
            if(hour > 12)
                hour -= 12
            snackTime.text = hour.toString()
            snackType.text = mealType[1]

            hour += 3
            if(hour > 12)
                hour -= 12
            dinnerTime.text = hour.toString()
            dinnerType.text = mealType[2]
        }

        btnBack.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        //Fire hidekeyboard when user taps outside any text object
//Place below code right before last right bracket in function onCreate
        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }
    }

    //function to hide keyboard goes right before the last right bracket of Class MainActivity
//should auto import android.content.Context
//should auto add import android.view.inputmethod.InputMethodManager
    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }


    }

    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        return sdf.format(Date())
    }
}
