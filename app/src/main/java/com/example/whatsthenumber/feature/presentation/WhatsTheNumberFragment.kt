package com.example.whatsthenumber.feature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.whatsthenumber.R
import com.example.whatsthenumber.common.extensions.hideKeyboard
import com.example.whatsthenumber.databinding.FragmentWhatsTheNumberBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WhatsTheNumberFragment : Fragment() {

    private val binding by lazy { FragmentWhatsTheNumberBinding.inflate(layoutInflater) }
    private val viewModel: WhatsTheNumberViewModel by viewModel()
    private var numberRequest: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleObserver()
        handleNewGame()
        textInputCounter()

        binding.send.setOnClickListener {
            binding.guessDisplay.text = binding.guess.text
            val numberGuess = binding.guess.text.toString().toInt()
            compareNumbers(numberRequest, numberGuess)
        }
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
        numberRequest = number
    }

    private fun handlerError(errorCode: String) {
        with(binding) {
            guessDisplay.text = errorCode
            guessResult.text = getString(R.string.error)
            newGame.isGone = false
            send.isEnabled = false
            guess.isEnabled = false
        }
    }

    private fun compareNumbers(numberRequest: Int?, numberGuess: Int) {
        if (numberRequest != null) {
            with(binding) {
                when {
                    numberGuess > numberRequest ->
                        guessResult.text = getString(R.string.is_smaller)
                    numberGuess == numberRequest -> {
                        guessResult.text = getString(R.string.right)
                        newGame.isGone = false
                        send.isEnabled = false
                    }
                    else ->
                        guessResult.text = getString(R.string.is_bigger)
                }
            }
        }
    }

    private fun handleNewGame() {
        with(binding) {
            newGame.setOnClickListener {
                viewModel.getNumber()
                newGame.isGone = true
                send.isEnabled = true
                guess.isEnabled = true
                guessDisplay.text = "0"
                guessResult.text = ""
                guess.text?.clear()
                hideKeyboard()
            }
        }
    }

    private fun textInputCounter() {
        with(binding) {
            guess.doAfterTextChanged {
                textInputCounter.text = getString(
                    R.string.label_input_counter, it?.length.toString()
                )
            }
        }
    }
}