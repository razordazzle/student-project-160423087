package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentproject.R
import com.example.studentproject.databinding.FragmentStudentListBinding
import com.example.studentproject.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private lateinit var viewModel: ListViewModel
    private val adapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        // subscribe / observe array list
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            adapter.updateStudentList(it)
        })

        // observer status error
        viewModel.loadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        // observer status progress
        viewModel.progressLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}