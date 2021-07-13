package com.baidu.iov.duchat.ui.view

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.baidu.iov.duchat.R

class ConversationBar(context: Context?) : LinearLayout(context) {
    init {
        val lp: ViewGroup.LayoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutParams = lp
        LayoutInflater.from(context).inflate(R.layout.float_conversation_view, this)
        setBackgroundResource(R.drawable.contact_bg)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }
}