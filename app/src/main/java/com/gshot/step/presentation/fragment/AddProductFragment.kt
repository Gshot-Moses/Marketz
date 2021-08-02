package com.gshot.step.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gshot.step.R
import com.gshot.step.model.Category
import com.gshot.step.model.Product
import com.gshot.step.presentation.viewmodel.AddProductViewModel

class AddProductFragment: Fragment() {

    var fragmentView: View? = null
    val viewModel: AddProductViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        fragmentView = inflater.inflate(R.layout.fragment_add_product, container, false)
        val map: MutableMap<Int, String> = mutableMapOf()
        val spinner = fragmentView!!.findViewById<Spinner>(R.id.category_spinner)
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        viewModel.getAllCategories().observe(viewLifecycleOwner, {
            val items = it.map { it.name }
            it.forEach { category ->  map[category.id.toInt()] = category.name }
            adapter.addAll(items)
        })
        val button = fragmentView!!.findViewById<Button>(R.id.btn_add)
        val productName = fragmentView!!.findViewById<EditText>(R.id.product_name)
        val image = fragmentView!!.findViewById<EditText>(R.id.product_image)
        val description = fragmentView!!.findViewById<EditText>(R.id.product_description)
        val price = fragmentView!!.findViewById<EditText>(R.id.product_price)
        button.setOnClickListener {
            val selection = spinner.selectedItem as String
            val results = map.filter { it.value == selection }.map { Category(it.key.toLong(), it.value) }
            val product = Product(0, results[0].id, productName.text.toString(),
                        image.text.toString(), description.text.toString(), price.text.toString().toFloat())
            viewModel.addProduct(product).observe(viewLifecycleOwner, {
                if (it > 0) {
                    Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addProductFragment_to_productFragment)
                }
                else
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            })
        }
        return fragmentView
    }
}