package com.example.jobsapplication.ui.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.jobsapplication.R
import com.example.jobsapplication.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding : FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

    }

}