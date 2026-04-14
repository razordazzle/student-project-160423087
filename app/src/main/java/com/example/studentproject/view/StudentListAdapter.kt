package com.example.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studentproject.databinding.StudentItemCardBinding
import com.example.studentproject.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtStudentName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionDetailFragment()
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    class StudentViewHolder(var binding: StudentItemCardBinding): RecyclerView.ViewHolder(binding.root)
}