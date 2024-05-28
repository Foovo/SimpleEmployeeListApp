package com.foovo.job

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class AddEmployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_employee)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn: Button = findViewById(R.id.btnAdd)

        btn.setOnClickListener {

            val name = findViewById<EditText?>(R.id.textName).text.toString()
            val surname = findViewById<EditText?>(R.id.textSurname).text.toString()
            val age = findViewById<EditText?>(R.id.textAge).text.toString()

            val rg: RadioGroup = findViewById(R.id.radio_group)
            val err: TextView = findViewById(R.id.textErr)


            val genderId = rg.checkedRadioButtonId
            val genderMale = when (genderId) {
                R.id.rbMale -> 1
                R.id.rbFemale -> 0
                else -> -1
            }

            var ageInt: Int? = null
            var errorMessage: String? = null

            if (name.isEmpty() || surname.isEmpty() || age.isEmpty() || genderMale == -1) {
                errorMessage = "ERROR: Please enter all fields."
            } else {
                try {
                    ageInt = age.toInt()
                } catch (e: NumberFormatException) {
                    errorMessage = "ERROR: Age must be a valid number."
                }
            }

            if (errorMessage != null) {
                err.text = errorMessage
            } else {

                val intent = Intent()
                intent.putExtra("name", name)
                intent.putExtra("surname", surname)
                intent.putExtra("age", ageInt)

                if(genderMale == 1) {
                    intent.putExtra("gender", true)
                }
                else{
                    intent.putExtra("gender", false)
                }


                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}