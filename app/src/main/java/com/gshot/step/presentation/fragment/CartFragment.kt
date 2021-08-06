package com.gshot.step.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.Utils
import com.gshot.step.presentation.adapter.CartAdapter
import com.gshot.step.presentation.viewmodel.CartFragmentViewModel

@Suppress("UNCHECKED_CAST")
class CartFragment: Fragment() {

    private var fragmentView: View? = null
    private val adapter: CartAdapter by lazy { CartAdapter() }
    private val viewModel: CartFragmentViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        fragmentView = inflater.inflate(R.layout.fragment_cart, container, false)
        val recyclerView = fragmentView!!.findViewById<RecyclerView>(R.id.recycler_cart)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        adapter.setQtyUpdateListener {
            viewModel.updateProductQty(it, Utils.cart!!.id)
        }
        adapter.setOnRemoveProductCartFromCartListener {
            viewModel.removeProductFromCart(Utils.cart!!.id, it.id, 1)
        }
        viewModel.getProducts(Utils.cart!!.id).observe(viewLifecycleOwner, {
            if (it != null) {
                Log.d("cart", "${it.size}")
                //Log.d("cart", "${array}")
                adapter.setData(it)
            }
        })
        return fragmentView
    }
}