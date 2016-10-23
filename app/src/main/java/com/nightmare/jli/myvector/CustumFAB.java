package com.nightmare.jli.myvector;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.ImageButton;


/**
 * Created by J.Li on 2016/6/19.
 */
public class CustumFAB extends ImageButton {

    Context mContect;
    int bg_Color;
    int bg_Color_Pressed;

    StateListDrawable sld;
    public CustumFAB(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContect=context;
        init(attrs);
    }

    public CustumFAB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContect=context;
        init(attrs);
    }

    public CustumFAB(Context context) {
        super(context);
        mContect=context;
        init();
    }

    private void init(AttributeSet attrs){
        Resources.Theme theme=mContect.getTheme();
        TypedArray array=theme.obtainStyledAttributes(attrs, R.styleable.FAB,0,0);

        try {
            setBg_Color(array.getColor(R.styleable.FAB_bg_color,Color.BLUE));

            setBg_Color_Pressed(array.getColor(R.styleable.FAB_bg_color_pressed,Color.GRAY));

            sld=new StateListDrawable();
            sld.addState(new int[]{android.R.attr.state_pressed},createButton(bg_Color_Pressed));
            sld.addState(new int[]{},createButton(bg_Color));
            setBackground(sld);
        }catch (Exception e){

        }finally {
            array.recycle();
        }
    }

    private void init(){
        Resources.Theme theme=mContect.getTheme();

        try {
            setBg_Color(Color.BLUE);

            setBg_Color_Pressed(Color.GRAY);

            sld=new StateListDrawable();
            sld.addState(new int[]{android.R.attr.state_pressed},createButton(bg_Color_Pressed));
            sld.addState(new int[]{},createButton(bg_Color));
            setBackground(sld);
        }catch (Exception e){

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpaceSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthSpaceMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMode=MeasureSpec.getMode(heightMeasureSpec);

        if(widthSpaceMode==MeasureSpec.AT_MOST&&heightSpaceMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(DensityUtil.dp2px(mContect,56),DensityUtil.dp2px(mContect,56));
        }else if(widthSpaceMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(DensityUtil.dp2px(mContect,56),heightSpaceSize);
        }else if(heightSpaceMode==MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpaceSize, DensityUtil.dp2px(mContect, 56));
        }
    }

    public void setBg_Color(int bg_Color) {
        this.bg_Color =bg_Color;
    }

    public void setBg_Color_Pressed(int bg_Color_Pressed) {
        this.bg_Color_Pressed = bg_Color_Pressed;
    }

    private Drawable createButton(int color){
        OvalShape ovalShape=new OvalShape();
        ShapeDrawable shapeDrawable=new ShapeDrawable(ovalShape);
        setWillNotDraw(false);
        shapeDrawable.getPaint().setColor(color);

        OvalShape ovalShape1=new OvalShape();
        ShapeDrawable shapeDrawable1=new ShapeDrawable(ovalShape1);
        shapeDrawable1.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                LinearGradient linearGradient=new LinearGradient(0,0,0,height,
                        new int[]{
                                Color.WHITE,
                                Color.LTGRAY,
                                Color.GRAY
                        },null,Shader.TileMode.REPEAT);
                return linearGradient;
            }
        });
        LayerDrawable layerDrawable=new LayerDrawable(new Drawable[]{shapeDrawable1,shapeDrawable});
        layerDrawable.setLayerInset(0,1,1,0,0);
        layerDrawable.setLayerInset(1,0,0,1,1);
        return layerDrawable;
    }
}
