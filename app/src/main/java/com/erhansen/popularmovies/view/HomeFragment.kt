package com.erhansen.popularmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.erhansen.popularmovies.adapter.HomeRecyclerAdapter
import com.erhansen.popularmovies.databinding.FragmentHomeBinding
import com.erhansen.popularmovies.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        homeRecyclerAdapter = HomeRecyclerAdapter(requireContext())
        with(fragmentHomeBinding) {
            homeRecyclerView.adapter = homeRecyclerAdapter
        }

        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.getDataFromAPI()
    }




}