package com.foovo.job

import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EmployeeListAdapter (
    private val employees: MutableList<Employee>
) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeListViewHolder>() {
    class EmployeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayed : TextView = itemView.findViewById(R.id.tvEmployeeTitle)
        val trash : FloatingActionButton = itemView.findViewById(R.id.btnDelEmployee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        return EmployeeListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.task_employee,
                parent,
                false
            )
        )
    }

    fun addEmployee(employee: Employee) {
        employees.add(employee)
        notifyItemInserted(employees.size-1)
    }

    fun delEmployee(employee: Employee)  {
        var ix : Int = employees.indexOf(employee)
        employees.remove(employee)
        notifyItemRemoved(ix)
    }

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        val curEmployee = employees[position]

        var gend = "Female"
        if(curEmployee.genderMale) {
            gend = "Male"
        }

        holder.displayed.text = "${curEmployee.name} ${curEmployee.surname}: ${gend}, ${curEmployee.age}"

        holder.trash.setOnClickListener{
            delEmployee(curEmployee)
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }
}