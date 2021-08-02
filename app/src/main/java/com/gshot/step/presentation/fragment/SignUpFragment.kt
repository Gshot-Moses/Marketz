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
import androidx.navigation.fragment.findNavController
import com.gshot.step.domain.AuthenticationService
import com.gshot.step.R
import com.gshot.step.Utils

class SignUpFragment: Fragment() {

    var fragmentView: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {
        fragmentView = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val usernameEt = fragmentView!!.findViewById<EditText>(R.id.username)
        val emailEt = fragmentView!!.findViewById<EditText>(R.id.email)
        val passwordEt = fragmentView!!.findViewById<EditText>(R.id.password)
        val signUpBtn = fragmentView!!.findViewById<Button>(R.id.sign_up_btn)
        signUpBtn.setOnClickListener {
            val username = usernameEt.text.toString()
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()
            if (!TextUtils.isEmpty(username) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                val user = AuthenticationService.getInstance(requireContext()).signUp(username, email, password)
                if (user != null) {
                    Utils.currentUser = user
                    findNavController().navigate(R.id.action_signUpFragment_to_categoryFragment)
                }
                else
                    Toast.makeText(requireContext(), "Sign up failed", Toast.LENGTH_SHORT).show()
            }
        }
        return fragmentView
    }
}