package com.example.kotlindatabindingviewmodel

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val buttonText: MutableLiveData<String> = MutableLiveData()
    val context: Context = application

    init {
        loading.value = false
        buttonText.value = application.getString(R.string.loading)

    }

    fun click() {
        val isLoading: Boolean = loading.value == true
        loading.postValue(!isLoading)

        buttonText.postValue(context.getString(if (isLoading) R.string.loading else R.string.cancel))
    }
}