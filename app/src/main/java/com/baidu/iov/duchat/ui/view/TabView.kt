package com.baidu.iov.duchat.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.baidu.iov.duchat.R

open class TabView : AppCompatTextView {
    var mIsChosen = false
//        set(value) {
//            mIsChosen = value
//            invalidate()
//        }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        var a = context?.obtainStyledAttributes(attrs, R.styleable.TabView)
        mIsChosen = a?.getBoolean(R.styleable.TabView_isChosen, false)!!
    }

    private val mPaint = Paint()

    init {
        mPaint.color = Color.parseColor("#009ADC")
        mPaint.isAntiAlias = true

    }



    override fun onDraw(canvas: Canvas?) {

        var colors = intArrayOf(Color.parseColor("#ffffff"),
            Color.parseColor("#0056DC")
            , Color.parseColor("#000000"))
        var stops = floatArrayOf(0.97f, 0.99f, 1f)

        if (mIsChosen) {
            val viewWidth = width.toFloat()
            val gradient = RadialGradient(
                viewWidth / 2, 2.5f * viewWidth + 80, 2.5f * viewWidth,
                colors, stops, Shader.TileMode.CLAMP
            )

            mPaint.shader = gradient
            canvas?.drawCircle(viewWidth / 2, 2.5f * viewWidth + 80, 2.5f * viewWidth, mPaint)
        }

        super.onDraw(canvas)

    }

    open fun setChosen(chosen : Boolean) {
        mIsChosen = chosen
        invalidate()
    }
}