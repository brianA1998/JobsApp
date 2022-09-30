package com.example.jobsapplication.ui.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jobsapplication.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jobsapplication.core.Result
import com.example.jobsapplication.data.remote.home.HomeRemoteDataSource
import com.example.jobsapplication.databinding.FragmentHomeScreenBinding
import com.example.jobsapplication.domain.home.HomeRepositoryImpl
import com.example.jobsapplication.ui.view.home.adapter.HomeScreenAdapter
import com.example.jobsapplication.ui.viewmodel.home.HomeScreenViewModel


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModel.Factory(HomeRepositoryImpl(HomeRemoteDataSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.getAllPublications().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE

                    if (result.data.isEmpty()) {
                        binding.emptyContainer.visibility = View.VISIBLE
                        return@Observer
                    } else {
                        binding.emptyContainer.visibility = View.GONE
                    }

                    binding.rvHome.adapter = HomeScreenAdapter(result.data)

                }

                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("ERROR_FIREBASE", "${result.exception}")
                }
            }
        })
    }

}