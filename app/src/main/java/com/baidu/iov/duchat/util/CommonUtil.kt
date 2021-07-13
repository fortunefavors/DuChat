package com.baidu.iov.duchat.util

import android.util.Log
import java.lang.reflect.ParameterizedType

/**
 * 通用工具类
 *
 * @author wangjian
 * Created on 2020/11/1 14:50
 */
object CommonUtil {
    /**
     * 通过反射，自动实例化泛型中的class对象
     */
    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取父类泛型 (T) 对应 Class类
        Log.i("nlg", t.javaClass.genericSuperclass.toString())
        return (t.javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0]
                as Class<T>
    }
}