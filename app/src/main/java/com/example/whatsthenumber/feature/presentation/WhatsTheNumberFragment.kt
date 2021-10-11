package com.example.whatsthenumber.feature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsthenumber.R
import com.example.whatsthenumber.databinding.FragmentWhatsTheNumberBinding

class WhatsTheNumberFragment : Fragment() {

    private val binding by lazy { FragmentWhatsTheNumberBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

}