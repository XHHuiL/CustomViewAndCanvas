package com.example.customviewandcanvas

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class MyCircleProgress : View {
    private var mWidth = 0
    private var mHeight = 0
    private var progress = 0.6

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {

    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        when(widthMode) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY -> mWidth = widthSize
            MeasureSpec.AT_MOST -> mWidth = dp2px(200)
        }
        when(heightMode) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY -> mHeight = heightSize
            MeasureSpec.AT_MOST -> mHeight = dp2px(200)
        }
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            val paint  = Paint().apply {
                color = 0xff00ffff.toInt()
                isAntiAlias = true
                this.strokeWidth = 10F
                style = Paint.Style.STROKE
            }
            val rectF = RectF(10F + paddingLeft, 10F + paddingTop,
                (mWidth - 10 - paddingRight).toFloat(), (mHeight - 10 - paddingBottom).toFloat())
            drawArc(rectF, -90F, (360 * progress).toFloat(), false, paint)
            paint.color = 0xff000000.toInt()
            paint.style = Paint.Style.FILL
            paint.textSize = 50F
            drawText("${(100 * progress).toInt()} %", (mWidth / 2).toFloat(), (mHeight / 2).toFloat(), paint)
        }
    }

}