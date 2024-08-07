package com.example.pos_demo_mvvm.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pos_demo_mvvm.databinding.FragmentProfileBinding
import com.example.pos_demo_mvvm.ui.auth.login.LoginActivity
import com.example.pos_demo_mvvm.viewmodel.AuthViewModel
import com.example.pos_demo_mvvm.utils.token.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val tokenManager = TokenManager(requireContext())

        binding.logOutBtn.setOnClickListener {
            tokenManager.clearToken()
            startActivity(
                Intent(requireContext(), LoginActivity::class.java)
            )
        }
    }

}