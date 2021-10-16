package com.example.whatsthenumber.feature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.whatsthenumber.databinding.FragmentWhatsTheNumberBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class WhatsTheNumberFragment : Fragment() {

    private val binding by lazy { FragmentWhatsTheNumberBinding.inflate(layoutInflater) }
    private val viewModel: WhatsTheNumberViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleObserver()
    }

    private fun handleObserver() {
        viewModel.numberLiveData.observe(
            viewLifecycleOwner, Observer(
                ::handleNumber
            )
        )

        viewModel.errorLiveData.observe(
            viewLifecycleOwner, Observer(
                ::handlerError
            )
        )
    }

    private fun handleNumber(number: Int) {
        binding.numberRequest.text = number.toString()
    }

    private fun handlerError(errorCode: String) {
        binding.numberRequest.text = errorCode
    }

    private fun compareNumbers() {

    }
}