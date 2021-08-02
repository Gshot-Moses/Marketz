package com.gshot.step.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gshot.step.domain.CartService
import com.gshot.step.R
import com.gshot.step.Utils
import com.gshot.step.model.Product

class ProductDetailsFragment: Fragment() {

    private var fragmentView: View? = null
    private var product: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_product_details, container, false)
        val titleTv = fragmentView!!.findViewById<TextView>(R.id.product_name)
        val addToCartBtn = fragmentView!!.findViewById<Button>(R.id.cart_btn)
        titleTv.text = product!!.productName
        addToCartBtn.setOnClickListener {
            if (Utils.currentUser == null)
                createDialog().show()
            else {
                if (CartService.getInstance(requireContext()).isProductInCart(product!!)) {
                    Toast.makeText(requireContext(), "Product already in cart", Toast.LENGTH_SHORT).show()
                }
                else {
                    CartService.getInstance(requireContext()).addProductToCart(product!!, 1, Utils.currentUser!!.id)
                    Toast.makeText(requireContext(), "Product added to cart", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return fragmentView
    }

    private fun createDialog(): AlertDialog {
        return AlertDialog.Builder(requireContext())
                .setMessage("You must register before adding an item to cart")
                .setTitle("Sign in")
                .setPositiveButton("Ok") { _, _ -> findNavController().navigate(R.id.action_productDetailsFragment_to_signInFragment) }
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable("product")
    }
}