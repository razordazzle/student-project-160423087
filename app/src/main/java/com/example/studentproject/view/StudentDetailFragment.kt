package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentproject.R
import com.example.studentproject.databinding.FragmentStudentDetailBinding
import com.example.studentproject.viewmodel.DetailViewModel
import com.example.studentproject.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewmodel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewmodel.fetch("1")

        observeViewModel()
    }

    fun observeViewModel() {
        viewmodel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtStudentID.setText(it.id)
            binding.txtStudentName.setText(it.name)
            binding.txtBoD.setText(it.bod)
            binding.txtPhone.setText(it.phone)
        })
    }
}