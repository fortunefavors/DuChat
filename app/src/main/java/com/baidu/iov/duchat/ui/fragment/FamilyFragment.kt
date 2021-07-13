package com.baidu.iov.duchat.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.baidu.iov.duchat.R
import com.baidu.iov.duchat.bean.BannerBean
import com.baidu.iov.duchat.databinding.FragmentFamilyBinding
import com.baidu.iov.duchat.util.GsonHelper
import com.baidu.iov.duchat.viewmodel.PageViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class FamilyFragment : Fragment() {
    lateinit var binding: FragmentFamilyBinding

    private val pageViewModel by viewModels<PageViewModel> ()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_family, container, false)
        pageViewModel.refreshData()
        pageViewModel.bannerList.observe(viewLifecycleOwner, Observer {
            binding.contentText.setText(GsonHelper.toJson(it))
        })

        return binding.root
    }

    companion object {
        fun newInstance() = FamilyFragment()
    }
}