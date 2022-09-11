package com.erhansen.popularmovies.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erhansen.popularmovies.adapter.WatchlistRecyclerAdapter
import com.erhansen.popularmovies.databinding.FragmentWatchlistBinding
import com.erhansen.popularmovies.viewmodel.WatchlistViewModel

class WatchlistFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var fragmentFavoriteBinding: FragmentWatchlistBinding
    private lateinit var watchlistRecyclerAdapter: WatchlistRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentFavoriteBinding = FragmentWatchlistBinding.inflate(layoutInflater)
        recyclerView = fragmentFavoriteBinding.watchlistRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3, GridLayoutManager.VERTICAL,false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchlistViewModel = ViewModelProvider(this)[WatchlistViewModel::class.java]
        watchlistViewModel.getWatchlistMovies(requireContext())
        observerLiveData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observerLiveData() {
        watchlistViewModel.watchlistMovies.observe(viewLifecycleOwner, Observer { watchlist ->
            if (watchlist.size == 0) {
                fragmentFavoriteBinding.errorMovieText.visibility = View.VISIBLE
            } else {
                fragmentFavoriteBinding.errorMovieText.visibility = View.GONE
                watchlistRecyclerAdapter = WatchlistRecyclerAdapter(requireContext(), watchlist)
                watchlistRecyclerAdapter.notifyDataSetChanged()
                recyclerView.adapter = watchlistRecyclerAdapter
            }
        })



    }



}