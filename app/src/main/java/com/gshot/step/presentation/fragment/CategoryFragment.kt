package com.gshot.step.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.presentation.adapter.CategoryAdapter
import com.gshot.step.presentation.viewmodel.CategoryFragmentViewModel

class CategoryFragment: Fragment(R.layout.fragment_category) {

    private val categoryViewModel: CategoryFragmentViewModel by viewModels()
    private var fragmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val adapter = CategoryAdapter()
        setRecyclerView(adapter)
        categoryViewModel.categories.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
        fragmentView = super.onCreateView(inflater, container, savedInstanceState)
        return fragmentView
    }

    private fun setRecyclerView(adapter: CategoryAdapter) {
        val recyclerView = fragmentView!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}