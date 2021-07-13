package com.baidu.iov.duchat.util

import com.google.gson.Gson

object GsonUtil {
    private val sGson = Gson()
    fun <T> fromJson(json: String?, type: Class<T>?): T? {
        return if (json == null) null else sGson.fromJson(json, type) as T
    }

    fun toJson(obj: Any?): String? {
        return if (obj == null) null else sGson.toJson(obj)
    }
}
