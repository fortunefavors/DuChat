package com.baidu.iov.duchat.ui.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class CircleImageView : AppCompatImageView {

    private lateinit var mPaint: Paint
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint.setAntiAlias(true)
//        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    override fun onDraw(canvas: Canvas) {
        val drawable = drawable
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            drawRoundByShaderMode(canvas, bitmap)
        } else {
            super.onDraw(canvas)
        }
    }

    private fun drawRoundByShaderMode(canvas: Canvas, bitmap: Bitmap) {
        //获取到Bitmap的宽高
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height

        //获取到ImageView的宽高
        val viewWidth = width
        val viewHeight = height
        val minScale =
            Math.min(viewWidth / bitmapWidth.toFloat(), viewHeight / bitmapHeight.toFloat())

//        canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(
            (viewWidth / 2).toFloat(),
            (viewWidth / 2).toFloat(),
            (viewWidth / 2).toFloat(),
            mPaint
        )
        //
//        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val matrix = Matrix()
        matrix.postScale(minScale, minScale)
        shader.setLocalMatrix(matrix)
        mPaint.setShader(shader)
        //        mPaint.setXfermode(xfermode);
        canvas.drawCircle(
            (viewWidth / 2).toFloat(),
            (viewWidth / 2).toFloat(),
            (viewWidth / 2).toFloat(),
            mPaint
        )
        mPaint.setXfermode(null)
//        canvas.restore();
    }
}