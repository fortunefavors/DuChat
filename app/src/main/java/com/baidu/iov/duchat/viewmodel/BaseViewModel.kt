package com.baidu.iov.duchat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidu.iov.duchat.model.BaseRepository
import com.baidu.iov.duchat.util.CommonUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

open class BaseViewModel<T : BaseRepository> : ViewModel(){
    fun getRepository() : T {
        return CommonUtil.getClass<T>(this).newInstance()
    }

    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }
}