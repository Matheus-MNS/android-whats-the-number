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
            viewModel.compareNumbers(numberGuess)
        }
    }

    private fun handleObserver() {
        viewModel.guessStateLiveData.observe(
            viewLifecycleOwner, Observer(
                ::compareNumbers
            )
        )
        viewModel.errorLiveData.observe(
            viewLifecycleOwner, Observer(
                ::handlerError
            )
        )
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

    private fun compareNumbers(state: GuessNumberStateEnum) {
        with(binding) {
            when (state) {
                GuessNumberStateEnum.IS_SMALLER ->
                    guessResult.text = getString(R.string.is_smaller)
                GuessNumberStateEnum.IS_EQUAL -> {
                    guessResult.text = getString(R.string.right)
                    newGame.isGone = false
                    send.isEnabled = false
                }
                GuessNumberStateEnum.IS_BIGGER ->
                    guessResult.text = getString(R.string.is_bigger)
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
                guessDisplay.text = getString(R.string.label_zero)
                guessResult.text = getString(R.string.label_zero)
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