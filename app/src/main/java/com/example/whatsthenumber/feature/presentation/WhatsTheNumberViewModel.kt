package com.example.whatsthenumber.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsthenumber.feature.domain.GetNumberUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class WhatsTheNumberViewModel(
    private val getNumberUseCase: GetNumberUseCase
) : ViewModel() {

    private var randomNumber = 0

    private val guessState: MutableLiveData<GuessNumberStateEnum> by lazy {
        MutableLiveData<GuessNumberStateEnum>()
    }

    val guessStateLiveData: LiveData<GuessNumberStateEnum>
        get() = guessState


    private val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val errorLiveData: LiveData<String>
        get() = error

    init {
        getNumber()
    }

    fun getNumber() {
        viewModelScope.launch {
            getNumberUseCase()
                .flowOn(Dispatchers.IO)
                .catch {
                    handleError(it)
                }
                .collect {
                    handleSuccess(it)

                }
        }
    }

    private fun handleError(throwable: Throwable) {
        var errorCode = ""
        if (throwable is HttpException) {
            errorCode = throwable.code().toString()
        }
        error.value = errorCode
    }

    private fun handleSuccess(random: Int) {
        randomNumber = random
    }

    fun compareNumbers(inputNumber: Int) {
        when {
            inputNumber > randomNumber ->
                guessState.value = GuessNumberStateEnum.IS_BIGGER
            inputNumber == randomNumber ->
                guessState.value = GuessNumberStateEnum.IS_EQUAL
            else ->
                guessState.value = GuessNumberStateEnum.IS_SMALLER
        }
    }
}