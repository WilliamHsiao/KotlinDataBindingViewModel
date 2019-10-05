package com.example.kotlindatabindingviewmodel

import android.app.Application
import android.content.Context
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.math.BigDecimal

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val text1: MutableLiveData<String> = MutableLiveData()
    val text2: MutableLiveData<String> = MutableLiveData()
    private var decimal: BigDecimal = BigDecimal(0)
    private var decimalb: BigDecimal = BigDecimal(0)
    private var d10: BigDecimal = BigDecimal(10)
    private val context: Context = application
    private var status: String = ""

    init {
        text1.value = ""
        text2.value = decimal.toString()
    }

    fun click(i: Int) {
        when(status){
            ""->
                decimal = decimal.multiply(d10).add(BigDecimal(i))
            "dot"->
                decimalb = decimalb.multiply(d10).add(BigDecimal(i))
        }
        text2.postValue(decimal.toString())
        text1.postValue(text1.value + String.format("%d", i))
    }

    fun plus() {
        text1.value ?: return
        if (text1.value!!.isEmpty()) return
        status = "plus"
        var text: String = text1.value!!
        if (!Character.isDigit(text.last())) {
            text.dropLast(1)
        }
        text += context.getString(R.string.Plus)
        text1.postValue(text)
    }

    fun dot() {
        if (status.equals("dot")) return
        status = "dot"
        text1.postValue(text1.value + context.getString(R.string.Dot))
    }

    fun minus() {

        status = "minus"
        text1.postValue(text1.value + context.getString(R.string.Minus))
    }

    fun equal() {
        if (status.isEmpty()) return
        status = ""
        text1.postValue(decimal.toString())
        decimal = BigDecimal(0)
        text2.postValue(decimal.toString())
    }

    fun multiplied() {

        status = "multiplied"
        text1.postValue(text1.value + context.getString(R.string.Multiplied))
    }

    fun divided() {


        status = "divided"
        text1.postValue(text1.value + context.getString(R.string.Divided))
    }
}