package com.example.administrator.timeviewdemo;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by win764-1 on 2016/11/1.
 */

public class TimeView extends View implements Runnable {

    private Context mContext;
    private Paint mPaint;
    private float mSecondDegree;
    private float mMinDegree;
    private float mHourDegree;
    private boolean mIsNight;
    private float mTotalSecond;
    private int borderColor;
    private int minScaleColor;
    private int midScaleColor;
    private int maxScaleColor;
    private float minScaleLength;
    private float midScaleLength;
    private float maxScaleLength;
    private int textColor;
    private float textSize;
    private int secondPointerColor;
    private int minPointerColor;
    private int hourPointerColor;
    private float secondPointerLength;
    private float minPointerLength;
    private float hourPointerLength;
    private int centerPointColor;
    private float centerPointSize;
    private float borderWidth;
    private float secondPointerSize;
    private float minPointerSize;
    private float hourPointerSize;
    private float centerPointRadiu;
    private String centerPointType;
    private int circleBackground;
    private int parentSize;
    private int sizeMode;
    private boolean isSecondGoSmooth;
    private int sleepTime;
    private int textHeight;
    private int textWight;
    private boolean isDrawText;

    public TimeView(Context context) {
        super(context);
        this.mContext = context;
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(context, attrs);
        initPainter();
    }

    /**
     * 初始化各参数
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, com.example.administrator.clock.R.styleable.TimeView);
        borderColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_borderColor, Color.BLACK);
        circleBackground = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_circleBackground, Color.WHITE);
        borderWidth = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_borderWidth,
                SizeUtils.dp2px(context, 1));
        minScaleColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_minScaleColor, Color.BLACK);
        midScaleColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_midScaleColor, Color.BLACK);
        maxScaleColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_maxScaleColor, Color.BLACK);
        minScaleLength = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_minScaleLength,
                SizeUtils.dp2px(context, 7));
        midScaleLength = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_midScaleLength,
                SizeUtils.dp2px(context, 12));
        maxScaleLength = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_maxScaleLength,
                SizeUtils.dp2px(context, 14));
        textColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_textColor, Color.BLACK);
        textSize = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_textSize,
                SizeUtils.dp2px(context, 15));
        isDrawText = ta.getBoolean(com.example.administrator.clock.R.styleable.TimeView_isDrawText,true);
        secondPointerColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_secondPointerColor, Color.RED);
        minPointerColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_minPointerColor, Color.BLACK);
        hourPointerColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_hourPointerColor, Color.BLACK);
        secondPointerLength = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_secondPointerLength,
                SizeUtils.dp2px(context, getWidth() / 3 * 2 / 3));
        minPointerLength = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_minPointerLength,
                SizeUtils.dp2px(context, getWidth() / 3 / 2));
        hourPointerLength = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_hourPointerLength,
                SizeUtils.dp2px(context, getWidth() / 3 / 3));
        secondPointerSize = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_secondPointerSize,
                SizeUtils.dp2px(context, 1));
        minPointerSize = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_minPointerSize,
                SizeUtils.dp2px(context, 3));
        hourPointerSize = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_hourPointerSize,
                SizeUtils.dp2px(context, 5));
        centerPointColor = ta.getColor(com.example.administrator.clock.R.styleable.TimeView_centerPointColor, Color.BLACK);
        centerPointSize = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_centerPointSize,
                SizeUtils.dp2px(context, 5));
        centerPointRadiu = ta.getDimension(com.example.administrator.clock.R.styleable.TimeView_centerPointRadiu,
                SizeUtils.dp2px(context, 1));
        centerPointType = ta.getString(com.example.administrator.clock.R.styleable.TimeView_centerPointType);
        isSecondGoSmooth = ta.getBoolean(com.example.administrator.clock.R.styleable.TimeView_isSecondGoSmooth,false);
        if(isSecondGoSmooth){
            sleepTime = 50;
        }else{
            sleepTime = 1000;
        }
        ta.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPainter() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0);
    }

    /**
     * 数字
     *
     * @param canvas
     * @param degree
     * @param text
     * @param paint
     */
    private void drawNum(Canvas canvas, int degree, String text, Paint paint) {
        Rect textBound = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBound);
        textHeight = textBound.bottom - textBound.top;
        textWight = textBound.right - textBound.left;
        canvas.rotate(degree);
        canvas.translate(0, maxScaleLength +textHeight*3f/4- getWidth() / 3);
        canvas.rotate(-degree);

//        canvas.drawText(text,-(textBound.right - textBound.left)/2,
//                (Math.abs(paint.ascent())-Math.abs(paint.descent()))/2,paint);
        canvas.drawText(text, -textWight / 2,
                textHeight / 2, paint);
        canvas.rotate(degree);
        canvas.translate(0, getWidth() / 3 - (maxScaleLength+textHeight*3f/4));
        canvas.rotate(-degree);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    /**
     * 测量控件尺寸
     * @param measureSpec
     * @return
     */
    private int measureSize(int measureSpec ){
        int result;
        parentSize = MeasureSpec.getSize(measureSpec);
        sizeMode = MeasureSpec.getMode(measureSpec);
        if(sizeMode == MeasureSpec.EXACTLY){
            result = parentSize;
        }else{
            result = SizeUtils.dp2px(mContext, 300);
            if (sizeMode == MeasureSpec.AT_MOST){
                result = Math.min(result,parentSize);
            }
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //外圆边界
        mPaint.setColor(borderColor);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3, mPaint);

        //圆背景
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(circleBackground);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3 - borderWidth, mPaint);

        //圆心
        mPaint.setColor(centerPointColor);
        mPaint.setStrokeWidth(centerPointSize);
        if (centerPointType.equals("rect")) {
            canvas.drawPoint(getWidth() / 2, getHeight() / 2, mPaint);
        } else if (centerPointType.equals("circle")) {
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, centerPointRadiu, mPaint);
        }

        //刻度
        canvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        for (int i = 0; i < 360; i++) {
            if (i % 30 == 0) {//长刻度
                mPaint.setColor(maxScaleColor);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 3,
                        getWidth() / 2, getHeight() / 2 - getWidth() / 3 + maxScaleLength, mPaint);
            } else if (i % 6 == 0) {//中刻度
                mPaint.setColor(midScaleColor);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 3,
                        getWidth() / 2, getHeight() / 2 - getWidth() / 3 + midScaleLength, mPaint);
            } else {//短刻度
                mPaint.setColor(minScaleColor);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 3,
                        getWidth() / 2, getHeight() / 2 - getWidth() / 3 + minScaleLength, mPaint);
            }
            canvas.rotate(1, getWidth() / 2, getHeight() / 2);
        }
        canvas.restore();


        //数字
        if(isDrawText){
            canvas.save();
            mPaint.setColor(textColor);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(1);
            mPaint.setTextSize(textSize);
            canvas.translate(getWidth() / 2, getHeight() / 2);
            for (int i = 0; i < 12; i++) {
                if (i == 0) {
                    drawNum(canvas, i * 30, 12 + "", mPaint);
                } else {
                    drawNum(canvas, i * 30, i + "", mPaint);
                }
            }
            canvas.restore();
        }
        //秒针
        canvas.save();
        mPaint.setColor(secondPointerColor);
        mPaint.setStrokeWidth(secondPointerSize);
        canvas.rotate(mSecondDegree, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2,
                getHeight() / 2 - secondPointerLength, mPaint);
        canvas.restore();
        canvas.save();

        //分针
        mPaint.setColor(minPointerColor);
        mPaint.setStrokeWidth(minPointerSize);
        canvas.rotate(mMinDegree, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2,
                getHeight() / 2 - minPointerLength, mPaint);
        canvas.restore();
        canvas.save();
        //时针
        mPaint.setColor(hourPointerColor);
        mPaint.setStrokeWidth(hourPointerSize);
        canvas.rotate(mHourDegree, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2,
                getHeight() / 2 - hourPointerLength, mPaint);
        canvas.restore();
    }

    /**
     * 手动设置时间
     *
     * @param hour
     * @param min
     */
    public void setTime(int hour, int min) {
        setTotalSecond(hour * 3600f + min * 60f);
    }

    /**
     * 应用打开初始化时间
     *
     * @param hour
     * @param min
     * @param second
     */
    public void setTime(int hour, int min, int second) {
        mMinDegree = min * 1.0f / 60 * 360;
        if (hour >= 24 || hour < 0 || min >= 60 || min < 0) {
            Toast.makeText(getContext(), "时间不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hour >= 12) {
            mIsNight = true;
            mHourDegree = min * 0.5f + (hour - 12) * 1.0f / 12 * 360;
        } else {
            mIsNight = false;
            mHourDegree = min * 0.5f + hour * 1.0f / 12 * 360;
        }
        mSecondDegree = second * 1.0f / 60 * 360;
    }

    /**
     * 获取时间对应的总秒数
     *
     * @return
     */
    public float getTimeTotalSecond() {
        if (mIsNight) {
            mTotalSecond = mHourDegree * 120 + 12 * 3600;
            return mTotalSecond;
        } else {
            mTotalSecond = mHourDegree * 120;
            return mTotalSecond;
        }
    }

    /**
     * 设置总时间（最大值为 24 * 3600）
     *
     * @param mTotalSecond
     */
    public void setTotalSecond(float mTotalSecond) {
        if (mTotalSecond >= (24 * 3600)) {
            this.mTotalSecond = mTotalSecond - 24 * 3600;
        } else {
            this.mTotalSecond = mTotalSecond;
        }
        int hour = (int) (mTotalSecond / 3600);
        int min = (int) ((mTotalSecond - hour * 3600) * 1.0 / 60);
        int second = (int) (mTotalSecond - hour * 3600 - min * 60);
        setTime(hour, min, second);
    }

    /**
     * 获取hour
     *
     * @return
     */
    public int getHour() {
        return (int) (getTimeTotalSecond() / 3600);
    }

    /**
     * 获取Min
     *
     * @return
     */
    public int getMin() {
        return (int) ((getTimeTotalSecond() - getHour() * 3600) / 60);
    }

    public int getSecond() {
        return (int) (getTimeTotalSecond() - getHour() * 3600 - getMin() * 60);
    }

    /**
     * 时钟走起
     */
    public void start(){
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (true) {
            postInvalidate();
            if (mSecondDegree == 360) {
                mSecondDegree = 0;
            }
            if (mMinDegree == 360) {
                mMinDegree = 0;
            }
            if (mHourDegree == 360) {
                mHourDegree = 0;
                mIsNight = !mIsNight;
            }
//            mSecondDegree = mSecondDegree + 0.3f;
//            mMinDegree = mMinDegree + 0.005f;
//            mHourDegree = mHourDegree + 1.0f / 2400.0f;
            mSecondDegree = mSecondDegree + 0.3f*sleepTime/50;
            mMinDegree = mMinDegree + 0.005f*sleepTime/50;
            mHourDegree = mHourDegree + 1.0f *sleepTime/50/ 2400.0f;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 尺寸转换工具类
     */
    private static class SizeUtils {
        public static int dp2px(Context context, float dp) {
            final float density = context.getResources().getDisplayMetrics().density;
            return (int) (dp * density + 0.5);
        }

        public static int px2dp(Context context, float px) {
            final float density = context.getResources().getDisplayMetrics().density;
            return (int) (px / density + 0.5);
        }
    }
}
