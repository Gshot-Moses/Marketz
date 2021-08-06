package com.gshot.step.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.Utils
import com.gshot.step.presentation.adapter.ProductAdapter
import com.gshot.step.presentation.viewmodel.ProductFragmentViewModel

class ProductFragment: Fragment() {

    private var fragmentView: View? = null
    private val viewModel: ProductFragmentViewModel by viewModels()
    private val adapter: ProductAdapter by lazy { ProductAdapter() }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        fragmentView = inflater.inflate(R.layout.fragment_product, container, false)
        setHasOptionsMenu(true)
        val recyclerView = fragmentView!!.findViewById<RecyclerView>(R.id.product_recycler_list)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle()
            bundle.putParcelable("product", it)
            findNavController().navigate(R.id.action_productFragment_to_productDetailsFragment, bundle)
        }
        if (viewModel.categoryId != -1) {
            viewModel.getProducts(viewModel.categoryId).observe(viewLifecycleOwner, {
                if (it != null) {
                    Log.d("product", "${it.size}")
                    adapter.setData(it)
                }
            })
        }
        else
            Toast.makeText(requireContext(), "id is not set", Toast.LENGTH_SHORT).show()
        return fragmentView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.categoryId == -1) {
            viewModel.categoryId = arguments!!.getInt("id")
            Log.d("id", "${viewModel.categoryId}")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_category -> {
                findNavController().navigate(R.id.action_productFragment_to_addProductFragment)
                true
            }
            R.id.cart_menu -> {
                findNavController().navigate(R.id.action_productFragment_to_cartFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}