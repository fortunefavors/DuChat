package com.baidu.iov.duchat.net

import com.baidu.iov.duchat.bean.BannerBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PageService {
    @GET("banner/json")
    suspend fun getMsgFromNet() : BaseResponse<List<BannerBean>>
}