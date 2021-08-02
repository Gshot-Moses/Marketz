package com.gshot.step.presentation.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gshot.step.domain.AuthenticationService
import com.gshot.step.R
import com.gshot.step.Utils
import com.gshot.step.domain.CartService

class SignInFragment: Fragment() {

    private var fragmentView: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        fragmentView = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val signUpText = fragmentView!!.findViewById<TextView>(R.id.sign_up_text)
        val authService = AuthenticationService.getInstance(requireContext())
        val emailEt = fragmentView!!.findViewById<EditText>(R.id.email)
        val passwordEt = fragmentView!!.findViewById<EditText>(R.id.password)
        val signInBtn = fragmentView!!.findViewById<Button>(R.id.sign_in_btn)
        signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        signInBtn.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                val user = authService.signIn(email, password)
                if (user != null) {
                    Utils.currentUser = user
                    if (!CartService.getInstance(requireContext()).checkCart(Utils.currentUser!!.id)) {
                        val cart = CartService.getInstance(requireContext()).createCart(Utils.currentUser!!.id)
                        Log.d("cart", "${cart}")
                        Utils.cart = cart
                    }
                    else {
                        val cart = CartService.getInstance(requireContext()).getCart(Utils.currentUser!!.id!!)
                        Utils.cart = cart
                    }
                    findNavController().navigate(R.id.action_signInFragment_to_categoryFragment)
                }
                else
                    Toast.makeText(requireContext(), "Sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
        return fragmentView
    }
}