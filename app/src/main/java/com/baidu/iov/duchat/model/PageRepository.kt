package com.baidu.iov.duchat.model

import com.baidu.iov.duchat.bean.BannerBean
import com.baidu.iov.duchat.net.BaseResponse
import com.baidu.iov.duchat.net.RetrofitClient
import com.baidu.iov.duchat.viewmodel.PageViewModel
import kotlinx.coroutines.flow.Flow

class PageRepository : BaseRepository() {

    suspend fun getBaidu(): BaseResponse<List<BannerBean>>
    = RetrofitClient.getInstance().getService().getMsgFromNet()
}