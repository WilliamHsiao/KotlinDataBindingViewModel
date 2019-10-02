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

class MainViewModel(application: Application) : ObservableViewModel(application) {


    @Bindable
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    @Bindable
    val buttonText: MutableLiveData<String> = MutableLiveData()
    private val context: Context = application

    init {
        loading.value = false
        buttonText.value = application.getString(R.string.loading)
        notifyChange(BR.viewModel)
    }

    fun click() {
        val isLoading: Boolean = loading.value == true
        loading.postValue(!isLoading)

        buttonText.postValue(context.getString(if (isLoading) R.string.loading else R.string.cancel))

    }
}