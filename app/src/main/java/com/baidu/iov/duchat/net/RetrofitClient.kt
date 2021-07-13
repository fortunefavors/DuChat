package com.baidu.iov.duchat.net

class RetrofitClient : AbsRetrofitClient<PageService>() {
    companion object{
        fun getInstance() : RetrofitClient{
            return SingletonHolder.INSTANCE
        }
    }
    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }
    override fun getBaseUrl(): String {
        return Constant.BASE_URL;
    }
}