package com.gshot.step.presentation.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gshot.step.R
import com.gshot.step.model.Category
import com.gshot.step.presentation.viewmodel.AddViewModel

class AddCategoryFragment: Fragment() {

    val viewModel: AddViewModel by viewModels()
    var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_add, container, false)
        val editText = fragmentView!!.findViewById<EditText>(R.id.category_title)
        val btn = fragmentView!!.findViewById<Button>(R.id.save_btn)
        btn.setOnClickListener {
            if(TextUtils.isEmpty(editText.text.toString())) {
                Toast.makeText(requireContext(), "Put title", Toast.LENGTH_SHORT).show()
            }
            else {
                val text = editText.text.toString()
                viewModel.addCategory(Category(0, text)).observe(viewLifecycleOwner, {
                    if (it > 0) {
                        Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_addFragment_to_categoryFragment)
                    }
                    else
                        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                })
            }
        }
        return fragmentView
    }
}