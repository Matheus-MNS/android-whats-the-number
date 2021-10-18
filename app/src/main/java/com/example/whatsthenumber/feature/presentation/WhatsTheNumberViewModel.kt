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
    private val getNumberUseCase: GetNumberUseCase) : ViewModel() {

  private val _numberLiveData: MutableLiveData<Int> by lazy {
      MutableLiveData<Int>()
    }

    val numberLiveData: LiveData<Int>
    get() = _numberLiveData



    val errorLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

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
                    _numberLiveData.value = it

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