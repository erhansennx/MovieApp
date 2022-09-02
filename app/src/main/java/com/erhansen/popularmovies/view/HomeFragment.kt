package com.erhansen.popularmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.erhansen.popularmovies.adapter.HomeRecyclerAdapter
import com.erhansen.popularmovies.databinding.FragmentHomeBinding
import com.erhansen.popularmovies.model.MovieModel
import com.erhansen.popularmovies.viewmodel.HomeViewModel

class HomeFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<MovieModel>
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        arrayList = ArrayList()
        homeRecyclerView = fragmentHomeBinding.homeRecyclerView
        homeRecyclerAdapter = HomeRecyclerAdapter(requireContext(),arrayList)
        homeRecyclerView.adapter = homeRecyclerAdapter

        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.refreshData()
        observeLiveData()
    }

    private fun observeLiveData() {
        homeViewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            it.let {
                fragmentHomeBinding.progressBar.visibility = View.GONE
                homeRecyclerView.visibility = View.VISIBLE
                for (i in it.results) {
                    arrayList.add(it)
                }
                homeRecyclerAdapter.notifyDataSetChanged()
            }
        })
        homeViewModel.popularMoviesError.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it) {
                    Toast.makeText(context,"Something Went Wrong!",Toast.LENGTH_SHORT).show()
                }
            }
        })
        homeViewModel.popularMoviesLoading.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it) {
                    fragmentHomeBinding.progressBar.visibility = View.VISIBLE
                    homeRecyclerView.visibility = View.GONE
                }
            }
        })
    }



}