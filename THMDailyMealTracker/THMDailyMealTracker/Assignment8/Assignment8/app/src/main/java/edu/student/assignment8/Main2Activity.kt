package edu.student.assignment8

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val txtshow = findViewById<TextView>(R.id.txtShow)
        val btnBack = findViewById<Button>(R.id.btnBack)
        //loads intent string from MainActivity.kt

        var strShow: String = intent.getStringExtra("SendStuff")
        var strName: String = intent.getStringExtra("SendStuff2")
        var hour = strShow.toInt()
        if(hour < 1 || hour > 12)
        {
            txtshow.text = "Invalid Input: value must be between 1 and 12... go back and try again!"
        }
        else
        {
            var outString = "Hello " + strName + "\nHere is a schedule for when to eat:\n\n"
            outString += "Breakfast: " + hour.toString() + "\n"
            hour += 3
            if(hour >  12)
                hour -= 12
            outString += "Lunch: " + (hour).toString() + "\n"
            hour += 3
            if(hour >  12)
                hour -= 12
            outString += "Snack: " + (hour).toString() + "\n"
            hour += 3
            if(hour >  12)
                hour -= 12
            outString += "Dinner: " + (hour).toString() + "\n"
            txtshow.text = outString
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
}
