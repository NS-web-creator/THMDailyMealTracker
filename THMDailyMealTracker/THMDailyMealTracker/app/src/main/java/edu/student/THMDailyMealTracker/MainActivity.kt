package edu.student.THMDailyMealTracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //make preferences
        val preferences = getSharedPreferences ("data", Context.MODE_PRIVATE)

        //bind objects
        val spin = findViewById<Spinner>(R.id.spinner)
        val btngo = findViewById<Button>(R.id.btnSubmit)
        val txtsend = findViewById<EditText>(R.id.txtSend)
        val txtsend2 = findViewById<EditText>(R.id.txtSend2)
        val txtsend3 = findViewById<Spinner>(R.id.spinner)
        val list = arrayOf ("E(CARBS: a lower-fat meal)", "S(FATS: a lower carb meal)", "FP(both: a lower-carb and a lower-fat meal)")
        val adapter1 = ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, list)
        android.R.layout.simple_spinner_item
        android.R.layout.simple_spinner_dropdown_item
        spin.adapter = adapter1

        //set text values from preferences if preferences exist
        txtsend.setText (preferences.getString ("hour", ""))
        txtsend2.setText(preferences.getString ("name", ""))

        btngo.setOnClickListener(View.OnClickListener {
            //save preferences
            val editor = preferences.edit ()
            editor.putString ("hour", txtsend.text.toString ())
            editor.putString("name", txtsend2.text.toString())
            editor.commit ()

            //Intent is used to send data between activities
            val intent = Intent(this, Main2Activity::class.java)
            //putExtra sets value to name SendStuff (Could be called whatever you want
            intent.putExtra("sendHour",txtsend.text.toString())
            intent.putExtra("sendName",txtSend2.text.toString())
            intent.putExtra("sendSpinner",spinner.selectedItem.toString())
            //Go to second activity
            startActivity(intent)

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
