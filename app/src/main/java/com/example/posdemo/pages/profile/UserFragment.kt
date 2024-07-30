package com.example.posdemo.pages.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.posdemo.databinding.FragmentUserBinding
import com.example.posdemo.retrofit.auth.TokenManager
import com.example.posdemo.services.auth.AuthServices

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.logOutButton.setOnClickListener {
            AuthServices.doLogOut(requireContext())
        }

        val tokenManager = TokenManager(requireContext())
        val token = tokenManager.getToken()

        Log.d("token_auth", token.toString())
    }
}