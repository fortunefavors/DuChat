package com.baidu.iov.duchat.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baidu.iov.duchat.R
import com.baidu.iov.duchat.viewmodel.PageViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class HistoryFragment : Fragment() {

    private val pageViewModel by viewModels<PageViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_histroy, container, false)
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}