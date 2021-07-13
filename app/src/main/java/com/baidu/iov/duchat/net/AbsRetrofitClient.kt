package com.baidu.iov.duchat.net

import com.baidu.iov.duchat.util.CommonUtil
import com.baidu.iov.duchat.util.LogUtils
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

open abstract class AbsRetrofitClient<T> {
    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .client(initOkhttp())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(getBaseUrl())
            .build()
    }

    private fun initOkhttp() : OkHttpClient {
        // 1 print log
        val loggingInterceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    try {
                        // 防止出现%号，解析异常
                        val text = URLDecoder.decode(
                            message.replace(
                                "%(?![0-9a-fA-F]{2})".toRegex(),
                                "%25"
                            ), "UTF-8"
                        )
                        LogUtils.d(text)
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                        LogUtils.d(message)
                    }
                }
            })

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(20L, TimeUnit.SECONDS)
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .addNetworkInterceptor(loggingInterceptor)
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
            .build()
    }

    abstract fun getBaseUrl() : String

    fun getService() : T{
        val a: Class<T> = CommonUtil.getClass(this)
        LogUtils.d(a.simpleName)
        return retrofit.create(a)
    }
}