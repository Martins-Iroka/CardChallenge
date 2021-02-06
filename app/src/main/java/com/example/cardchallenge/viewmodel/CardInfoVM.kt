package com.example.cardchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.model.Card
import com.example.domain.usecase.CardUseCase
import kotlinx.coroutines.launch

class CardInfoVM(private val cardUseCase: CardUseCase) : ViewModel() {

    val cardNumber = MutableLiveData<String?>()

    private val _cardInfo = MutableLiveData<Card?>()
    val cardInfo: LiveData<Card?> = _cardInfo

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getCardInfo() {
        _loading.value = true
        viewModelScope.launch {
            val result = cardUseCase.getCardInfo(cardNumber.value!!)

            if (result.status == Result.Status.SUCCESS && result.data != null) {
                _cardInfo.postValue(result.data!!)
            } else _cardInfo.value = null
            _loading.value = true
        }
    }

}