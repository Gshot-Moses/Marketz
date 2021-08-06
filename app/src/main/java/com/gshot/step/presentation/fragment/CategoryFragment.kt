package com.gshot.step.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.Utils
import com.gshot.step.presentation.adapter.CategoryAdapter
import com.gshot.step.presentation.viewmodel.CategoryFragmentViewModel

class CategoryFragment: Fragment() {

    private val categoryViewModel: CategoryFragmentViewModel by viewModels()
    private val adapter: CategoryAdapter by lazy{ CategoryAdapter() }
    private var fragmentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_category, container, false)
        setHasOptionsMenu(true)
        if (Utils.currentUser == null)
            findNavController().navigate(R.id.action_categoryFragment_to_signInFragment)
        val recyclerView = fragmentView!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        adapter.setItemClickedListener {
            val bundle = Bundle()
            bundle.putInt("id", it)
            findNavController().navigate(R.id.action_categoryFragment_to_productFragment, bundle)
        }
        categoryViewModel.getAll().observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.isNotEmpty()) {
                    adapter.setData(it)
                    Toast.makeText(requireContext(), "Not empty", Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(requireContext(), "List null", Toast.LENGTH_SHORT).show()
        })
        return fragmentView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_category -> {
                findNavController().navigate(R.id.action_categoryFragment_to_addFragment)
                true
            }
            R.id.cart_menu -> {
                findNavController().navigate(R.id.action_categoryFragment_to_cartFragment)
                true
            }
            R.id.search_menu -> {true}
            else -> return super.onOptionsItemSelected(item)
        }
    }
}