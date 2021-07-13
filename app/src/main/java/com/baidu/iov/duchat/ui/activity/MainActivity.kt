package com.baidu.iov.duchat.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.baidu.iov.duchat.R
import com.baidu.iov.duchat.databinding.ActivityMainBinding
import com.baidu.iov.duchat.ui.fragment.FamilyFragment
import com.baidu.iov.duchat.ui.fragment.HistoryFragment

class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val familiFragment by lazy { FamilyFragment.newInstance() }
    private var historyFragment: HistoryFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        initView()

    }

    private fun initView() {
        binding.tab1.setOnClickListener(this)
        binding.tab2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tab1 -> {
                binding.tab1.setChosen(true)
                binding.tab2.setChosen(false)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, familiFragment!!)
                    .commitNow()
            }
            R.id.tab2 -> {
                binding.tab1.setChosen(false)
                binding.tab2.setChosen(true)

                if (historyFragment == null) {
                    historyFragment = HistoryFragment.newInstance()
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, historyFragment!!)
                    .commitNow()
            }
        }
    }
}