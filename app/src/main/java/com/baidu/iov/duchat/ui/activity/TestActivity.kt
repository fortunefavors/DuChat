package com.baidu.iov.duchat.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.baidu.iov.duchat.R
import com.baidu.iov.duchat.bean.TestData
import com.baidu.iov.duchat.databinding.ActivityTestBinding

class TestActivity : BaseActivity() {
    private lateinit var binding : ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("nlg1108", "onCreate")
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_test)

        var testData = TestData("jacky", 18)

        binding.user = testData

        testData.name = "mike"


    }
//
//    @BindingAdapter
//    fun showTest() {
//        Log.i("nlg1108", "name change")
//    }
}