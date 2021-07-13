package com.baidu.iov.duchat.viewmodel

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import com.baidu.iov.duchat.bean.BannerBean
import com.baidu.iov.duchat.model.PageRepository
import com.baidu.iov.duchat.net.BaseResponse
import com.baidu.iov.duchat.net.RetrofitClient
import com.baidu.iov.duchat.util.GsonHelper
import com.baidu.iov.duchat.util.LogUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File

private fun File.saveTempFile() = bundleOf(Pair("path", absolutePath))

class PageViewModel (private val savedStateHandle: SavedStateHandle):
    BaseViewModel<PageRepository>() {

    fun refreshData() = launchUI {
        var list: BaseResponse<List<BannerBean>>?= null
            try {
            LogUtils.d("refresh start")
            list = getRepository().getBaidu()
            LogUtils.d("refresh list:" + GsonHelper.toJson(list))
        } catch (e: Exception) {
            LogUtils.d("refresh error:" + e.message)
        }
        bannerList.value = list?.data()
    }


    private var tempFile: File? = null

    init {
        savedStateHandle.setSavedStateProvider("temp_file") {
            tempFile?.saveTempFile() ?: Bundle()
        }
    }
    var bannerList = MutableLiveData<List<BannerBean>>()

}