package com.example.administrator.sample.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.sample.R;

import java.util.ArrayList;


/**
 *
 *
 * Created by shizi on 2017/8/2.
 * 自定义分数折线图，分数类型为：float 类型
 */

public class LineChartSingleFloat extends View {

    private final int LeftTopX = 90;      //左上 x
    private final int LeftTopY = 70;      //左上 y
    private final int leftBottomY = 470;  //左下 y
    private final int rightBottomY = 790; //右下 y

    private final int verticalSpace = 100;
    private final int horizentalSpace = 100;

    private int verticalLines;        //垂直间隔线条数：  4
    private int horizentalLines;  //水平间隔线条数：    7

    private ArrayList<Float> XPoint = new ArrayList<>();        // x 轴坐标

    private ArrayList<Float> YValue = new ArrayList<>();        // y 轴坐标值
    private ArrayList<Float> YActualValue = new ArrayList<>();        // 根据实际成绩计算出的 y 轴坐标值
    private float yMaxValue;                                    // y 轴最大值
    private float currentVerticalSpace;                         // 按分数划分的垂直间隔

    private int colorXY;              // 坐标轴颜色
    private int colorSpace;           // 间隔线颜色
    private int colorLine;            // 折线颜色
    private int colorText;
    private float widthXY;
    private float widthLine;            // 折线宽度

    private float sizeText;

    private Paint mPaint;
    private Path path;

    private Path pathShader;
    private Paint paintShader;
    private final int[] shadeColors = new int[]{Color.parseColor("#c5dbf6"), Color.parseColor("#dae9f9"), Color.parseColor("#e3eefb")
            , Color.parseColor("#ffffffff")};               //  初始化渐变色
    private Shader mShader;

    public LineChartSingleFloat(Context context) {
        super(context);
    }

    public LineChartSingleFloat(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
        initType(context, attrs);
    }

    public LineChartSingleFloat(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
//        init();
    }

    private void initType(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineChart);
        colorXY = array.getColor(R.styleable.LineChart_colorXY, Color.parseColor("#bebebe"));
        colorSpace = array.getColor(R.styleable.LineChart_colorSPACE, Color.parseColor("#eae9e9"));
        colorLine = array.getColor(R.styleable.LineChart_colorLINE, Color.parseColor("#488def"));
        colorText = array.getColor(R.styleable.LineChart_colorTEXT, Color.parseColor("#787878"));

        widthXY = array.getDimension(R.styleable.LineChart_widthXY, 2f);
        widthLine = array.getDimension(R.styleable.LineChart_widthLINE, 6f);
        sizeText = array.getDimension(R.styleable.LineChart_sizeTEXT, 45f);

        array.recycle();
    }


    private void init() {
        path = new Path();
        pathShader = new Path();
        paintShader = new Paint();

        horizentalLines = (rightBottomY) / horizentalSpace;
        verticalLines = (leftBottomY - LeftTopY) / verticalSpace;
        for (int i = 0; i < horizentalLines; i++) {
            float x = LeftTopX + (i + 1) * 100;
            XPoint.add(x);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!listIsEmpty(YValue)) {
            initDate();
        }

        drawXY(canvas);

        drawVerticalSpaceLine(canvas);

        drawValue(canvas);
    }

    /**
     * 初始化数据
     */
    private void initDate() {

        if (listIsEmpty(YActualValue))
            YActualValue.clear();

        yMaxValue = getYMaxValue(YValue);
        float currentReminder = yMaxValue % 4;                      // 最大值对 4 取余数
        float currentQuotient = (yMaxValue / 4);              // 最大值对 4 求商
        if (currentReminder == 0) {                                   // 最大值为 4 的倍数
            currentVerticalSpace = currentQuotient;
            YActualValue.addAll(convertYValue(YValue, (int) currentVerticalSpace, (int) currentReminder));
        } else {
            currentVerticalSpace = currentQuotient + 1;
            YActualValue.addAll(convertYValue(YValue, (int) currentVerticalSpace, (int) currentReminder));
        }
    }

    private ArrayList<Float> convertYValue(ArrayList<Float> yCount, int verticalSpace, int lever) {
        ArrayList<Float> currentValueList = new ArrayList<>();
        for (float currentValue : yCount) {
            float valueLever = 70 + (4 - lever) * 100;
            float valueExtra = ((int) currentValue - verticalSpace * lever) * 100 / verticalSpace;
            float value = valueLever - valueExtra;
            currentValueList.add(value);
        }
        return currentValueList;
    }

    /**
     * 获取最高成绩
     *
     * @param yCount
     * @return
     */
    private float getYMaxValue(ArrayList<Float> yCount) {
        float currentMax = 0;
        for (int i = 0; i < yCount.size(); i++) {
            if (yCount.get(i) >= currentMax)
                currentMax = yCount.get(i);
        }
        return currentMax;
    }

    /**
     * 画点、线
     *
     * @param canvas canvas
     */
    private void drawValue(Canvas canvas) {

        mShader = new LinearGradient(0, 0, 0, getHeight(), shadeColors, null, Shader.TileMode.CLAMP);
        for (int i = 0; i < YValue.size() - 1; i++) {
            pathShader.moveTo(XPoint.get(i), YActualValue.get(i));
            pathShader.lineTo(XPoint.get(i + 1), YActualValue.get(i + 1));
            pathShader.lineTo(XPoint.get(i + 1), leftBottomY);
            pathShader.lineTo(XPoint.get(i), leftBottomY);
            pathShader.close();
            paintShader.setShader(mShader);
            canvas.drawPath(pathShader, paintShader);
        }

        // 画折线
        getPaint().setStrokeWidth(widthLine);
        getPaint().setColor(colorLine);
        for (int i = 0; i < YValue.size() - 1; i++) {
            canvas.drawLine(XPoint.get(i), YActualValue.get(i), XPoint.get(i + 1), YActualValue.get(i + 1), getPaint());
        }
        // 画出转折点圆圈
        getPaint().setColor(colorLine);
        for (int i = 0; i < YValue.size(); i++) {
            // 画外圆
            getPaint().setColor(colorLine);
            getPaint().setStyle(Paint.Style.FILL);// 设置为空心
            canvas.drawCircle(XPoint.get(i), YActualValue.get(i), 14, getPaint());
            // 画中心点为白色
            getPaint().setColor(Color.WHITE);
            getPaint().setStyle(Paint.Style.FILL);
            canvas.drawCircle(XPoint.get(i), YActualValue.get(i), 10, getPaint());
            getPaint().setColor(colorText);
//            canvas.drawText(String.valueOf(YValue.get(i)),XPoint.get(i),YActualValue.get(i) - 10,getPaint());     // 显示本次成绩结果
        }
    }

    /**
     * @param canvas canvas
     */
    private void drawVerticalSpaceLine(Canvas canvas) {
        /**
         * 垂直分割线
         */
        getPaint().setColor(colorSpace);

        for (int i = 0; i < verticalLines; i++) {
            float xStart = LeftTopX;
            float xEnd = rightBottomY;
            float yStart = LeftTopY + i * 100;
            float yEnd = LeftTopY + i * 100;
            float[] xLineFloat = {xStart, yStart, xEnd, yEnd};
            canvas.drawLines(xLineFloat, getPaint());
        }

        /**
         * 水平分割线:
         * 许多显示 7 次成绩，故，只需要画出 7 条水平分割线即可。
         */

        for (int i = 1; i <= horizentalLines; i++) {
            float xStart = LeftTopX + i * 100;
            float xEnd = LeftTopX + i * 100;
            float yStart = LeftTopY;
            float yEnd = leftBottomY;
            float[] xLineFloat = {xStart, yStart, xEnd, yEnd};
            canvas.drawLines(xLineFloat, getPaint());
        }

        getPaint().setColor(colorText);
        getPaint().setTextSize(sizeText);

        canvas.drawText((int) currentVerticalSpace * 4 + "", LeftTopX - 75, LeftTopY + 18, getPaint());
        canvas.drawText((int) currentVerticalSpace * 3 + "", LeftTopX - 55, LeftTopY + 118, getPaint());
        canvas.drawText((int) currentVerticalSpace * 2 + "", LeftTopX - 55, LeftTopY + 218, getPaint());
        canvas.drawText((int) currentVerticalSpace + "", LeftTopX - 55, LeftTopY + 318, getPaint());
        for (int i = 0; i < horizentalLines + 1; i++) {
            if (i == 0) {
                canvas.drawText("0", LeftTopX, leftBottomY + 60, getPaint());
//                continue;
            }else if(i == YValue.size()){
                getPaint().setTextSize(sizeText - 8);
                canvas.drawText("本次", LeftTopX + 60 + (i - 1) * 100, leftBottomY + 55, getPaint());
            }else{
                canvas.drawText(String.valueOf(i), LeftTopX + 85 + (i - 1) * 100, leftBottomY + 60, getPaint());
            }
        }

        canvas.drawText("(" + "次数" + ")", rightBottomY + 40, leftBottomY + 15, getPaint());
//        canvas.drawText("(" + "结果" + ")",LeftTopX - 35,LeftTopY - 30,getPaint());
    }

    /**
     * 坐标轴
     * @param canvas canvas
     */
    private void drawXY(Canvas canvas) {
        // 设置宽度
        getPaint().setStrokeWidth(widthXY);
        //坐标轴, 线的坐标点 （四个为一条线）
        float[] xyLines = {LeftTopX, LeftTopY - 20, LeftTopX, leftBottomY
                , LeftTopX, leftBottomY, rightBottomY + 20, leftBottomY};
        getPaint().setColor(colorXY);
        canvas.drawLines(xyLines, getPaint());

//   箭头      通过路径画三角形
        getPaint().setStyle(Paint.Style.FILL);// 设置为空心
        path.moveTo(LeftTopX - 5, LeftTopY - 20);// 此点为多边形的起点
        path.lineTo(LeftTopX + 5, LeftTopY - 20);
        path.lineTo(LeftTopX, LeftTopY - 35);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, getPaint());
        // 第二个箭头
        path.moveTo(rightBottomY + 20, leftBottomY - 5);// 此点为多边形的起点
        path.lineTo(rightBottomY + 20, leftBottomY + 5);
        path.lineTo(rightBottomY + 35, leftBottomY);
        canvas.drawPath(path, getPaint());
    }

    private Paint getPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        return mPaint;
    }

    public void setDate(ArrayList<Float> listScore) {
        if (listIsEmpty(listScore))
            return;
        if (YValue != null && !YValue.isEmpty())
            YValue.clear();
        this.YValue.addAll(listScore);
        invalidate();
    }

    /**
     * @param list
     * @return
     */
    private boolean listIsEmpty(ArrayList<Float> list) {
        return list == null || list.isEmpty();
    }
}