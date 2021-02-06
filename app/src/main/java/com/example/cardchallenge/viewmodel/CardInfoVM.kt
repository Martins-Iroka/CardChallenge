package com.example.cardchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.cardchallenge.util.EspressoIdlingResource
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

    fun getCard() {
        EspressoIdlingResource.wrapEspressoIdlingResource {
            _loading.value = true
            viewModelScope.launch {
                val result = cardUseCase.getCardInfo(cardNumber.value!!)

                if (result.status == Result.Status.SUCCESS && result.data != null) {
                    _cardInfo.postValue(result.data!!)
                } else {
                    _cardInfo.value = null
                    Log.e(this@CardInfoVM::class.simpleName, "${result.message}")
                }
                _loading.value = false

            }
        }
    }
}

class CardInfoVMFactory(
    private val useCase: CardUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CardInfoVM(useCase) as T
    }
}