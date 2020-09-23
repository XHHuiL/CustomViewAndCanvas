package com.example.customviewandcanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.graphics.drawable.toBitmap

class MyImageView : androidx.appcompat.widget.AppCompatImageView {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {

    }

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint().apply {
            color = 0xffffffff.toInt()
            isAntiAlias = true
        }
        val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        canvas?.apply {
            saveLayer(0F, 0F, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
            // 直接使用clipPath会有锯齿问题
            // canvas.clipPath(path)
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (width / 2 - 1).toFloat(), paint)
            val bitmap = drawable.toBitmap()
            val bitmapRect = Rect(0, 0, bitmap.width, bitmap.height)
            val canvasRect = Rect(0, 0, width, height)
            paint.xfermode = xfermode

            drawBitmap(bitmap, bitmapRect, canvasRect, paint)
            // 此次绘制完成后再恢复到原来的状态
            restore()
        }
    }
}