package com.nyt.nytimes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.snackbar.Snackbar
import com.nyt.nytimes.R
import com.nyt.nytimes.databinding.FragmentHomeBinding

import com.nyt.nytimes.ui.main.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    private val vModel: MainActivityViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpItems()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpItems() {
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(context)
        binding.rvNews.adapter = newsAdapter

        vModel.progress.observe(viewLifecycleOwner, {
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        })
        vModel.newsList.observe(viewLifecycleOwner, {
            it?.let {
                newsAdapter.updateList(it)
                newsAdapter.notifyDataSetChanged()
            }
        })
        vModel.errorMessage.observe(viewLifecycleOwner, {
            Snackbar.make(binding.root, it?: getString(R.string.something_wrong), Snackbar.LENGTH_LONG).show()
        })

        vModel.fetchArticles()
    }

}