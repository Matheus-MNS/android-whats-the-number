package com.example.whatsthenumber.feature.presentation

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

    val numberLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val errorLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        getNumber()
    }

    private fun getNumber() {
        viewModelScope.launch {
            getNumberUseCase()
                .flowOn(Dispatchers.IO)
                .catch {
                    handleError(it)
                }
                .collect {
                    numberLiveData.value = it
                }
        }

    }

    private fun handleError(throwable: Throwable) {
        var errorCode = ""
        if (throwable is HttpException) {
            errorCode = throwable.code().toString()
        }
        errorLiveData.value = errorCode
    }
}