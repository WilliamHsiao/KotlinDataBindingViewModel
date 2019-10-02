package com.example.kotlindatabindingviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindatabindingviewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel=viewModel

        viewModel.buttonText.observe(this,Observer {btn.text=it!!})
        viewModel.loading.observe(this,Observer {progressBar.visibility = if(it==true) View.VISIBLE else View.GONE })
    }
}
