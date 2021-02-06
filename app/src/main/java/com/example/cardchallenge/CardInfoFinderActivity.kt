package com.example.cardchallenge

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cardchallenge.databinding.CardInfoActivityBinding
import com.example.cardchallenge.viewmodel.CardInfoVM
import com.example.cardchallenge.viewmodel.CardInfoVMFactory


class CardInfoFinderActivity : AppCompatActivity() {
    private lateinit var cardInfoBinding: CardInfoActivityBinding

    private val viewModel by viewModels<CardInfoVM> {
        val cardUseCase = (applicationContext as CardInfoApplication).cardUseCase
        CardInfoVMFactory(cardUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardInfoBinding = DataBindingUtil.setContentView(this, R.layout.card_info_activity)
        cardInfoBinding.viewModel = viewModel
        cardInfoBinding.lifecycleOwner = this

        cardInfoBinding.cardNumberEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length!! == 16) {
                    cardInfoBinding.proceed.visibility = View.VISIBLE
                } else cardInfoBinding.proceed.visibility = View.INVISIBLE
            }
        })

        observers()
    }

    private fun observers() {
        viewModel.loading.observe(this) {
            if (it) {
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(cardInfoBinding.root.windowToken, 0)
                showHideView(cardInfoBinding.proceed, false)
                showHideView(cardInfoBinding.progress, true)
                showHideView(cardInfoBinding.cardInfoLayout, false)
                showHideView(cardInfoBinding.noDataView, false)
            } else {
                showHideView(cardInfoBinding.proceed, true)
                showHideView(cardInfoBinding.progress, false)
            }
        }

        viewModel.cardInfo.observe(this) {
            if (it != null) {
                showHideView(cardInfoBinding.cardInfoLayout, true)
                showHideView(cardInfoBinding.noDataView, false)
            } else {
                showHideView(cardInfoBinding.cardInfoLayout, false)
                showHideView(cardInfoBinding.noDataView, true)
            }
        }
    }

    private fun showHideView(view: View, show: Boolean) {
        if (show) view.visibility = View.VISIBLE else view.visibility = View.GONE
    }
}