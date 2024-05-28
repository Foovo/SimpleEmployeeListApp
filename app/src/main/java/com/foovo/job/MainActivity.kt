package com.foovo.job

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var employeeListAdapter : EmployeeListAdapter = EmployeeListAdapter(mutableListOf())

    private val getData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val name = result.data?.getStringExtra("name") ?: throw Exception("Name cannot be null")
            val surname = result.data?.getStringExtra("surname") ?: throw Exception("Surname cannot be null")
            val age = result.data?.getIntExtra("age", -1) ?: throw Exception("Age cannot be null")
            val isMale = result.data?.getBooleanExtra("gender", false) ?: throw Exception("Gender cannot be null")

            val emp = Employee(name, surname, age, isMale)
            employeeListAdapter.addEmployee(emp)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rv: RecyclerView = findViewById(R.id.rvEmployeeList)
        rv.adapter = employeeListAdapter
        rv.layoutManager = LinearLayoutManager(this)

        val btn: FloatingActionButton = findViewById(R.id.btnNew)
        btn.setOnClickListener{

            // switch view to add employee
            val intent = Intent(this,AddEmployee::class.java)
            getData.launch(intent)
        }
    }
}