package com.evounit.nike.passwordedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * Author:yaocl
 * Created on 2016/8/2.
 */
public class NKPasswordEditText extends EditText {

    private Drawable showIcon;
    private Drawable hideIcon;
    /*private Drawable labelIcon;
    private int labelIconSpace;*/

    private int colorAccent;//当前主题的colorAccent
    //private int labelIconSize;
    private int passwordIconSize;

    private Drawable currentIcon;//当前的显示的图标

    public NKPasswordEditText(Context context) {
        this(context,null);
    }

    public NKPasswordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public NKPasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDrawable(context,attrs,defStyleAttr);
    }

    private void initDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NKPasswordEditText, defStyleAttr, 0);

        showIcon = typedArray.getDrawable(R.styleable.NKPasswordEditText_showIcon);
        hideIcon = typedArray.getDrawable(R.styleable.NKPasswordEditText_hideIcon);
        passwordIconSize = (int) typedArray.getDimension(R.styleable.NKPasswordEditText_passwordIconSize,getTextSize());

        /*labelIcon = typedArray.getDrawable(R.styleable.NKPasswordEditText_labelIcon);
        labelIconSpace = (int) typedArray.getDimension(R.styleable.NKPasswordEditText_labelIconSpace,0f);
        labelIconSize = (int) typedArray.getDimension(R.styleable.NKPasswordEditText_labelIconSize,getTextSize());*/

        typedArray = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
        colorAccent = typedArray.getColor(0,0xFF00FF);
        typedArray.recycle();

        if ((showIcon == null && hideIcon != null)||(showIcon != null && hideIcon == null)) {
            currentIcon = null;
            throw new IllegalArgumentException("需要同时设置显示图标和隐藏图标");
        }

        /*//默认图标
        if (showIcon == null ) {
            showIcon = getResources().getDrawable(R.drawable.wifi_eye_kejian);
        }
        if (hideIcon == null) {
            hideIcon = getResources().getDrawable(R.drawable.wifi_eye);
        }*/
        if (showIcon != null && hideIcon != null) {
            showIcon.setBounds(0, 0, passwordIconSize, passwordIconSize);
            hideIcon.setBounds(0, 0, passwordIconSize, passwordIconSize);
            currentIcon = hideIcon;
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
        /*if (labelIcon != null) {
            labelIcon.setBounds(0, 0, labelIconSize, labelIconSize);
            setCompoundDrawables(labelIcon,null,null,null);
            setCompoundDrawablePadding(labelIconSpace);
        }*/
        //DrawableCompat.setTint(showIcon,colorAccent);
        //DrawableCompat.setTint(hideIcon,colorAccent);
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
       if (text.length() >0) {
            setCompoundDrawables(null,null,currentIcon,null);
        }else {
            setCompoundDrawables(null,null,null,null);
            currentIcon = hideIcon;//恢复初始状态
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (currentIcon !=null&&event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            boolean isInWidth = (x > getWidth() - getTotalPaddingRight())&&(x < getWidth() - getPaddingRight());

            Rect rect = currentIcon.getBounds();
            int height = rect.height();

            int space = (getHeight() - height)/2;
            boolean isInHeight = ( y > space && y < (space + height));
            if (isInHeight&&isInWidth) {
                toggleIcon();
            }
        }
        return super.onTouchEvent(event);
    }

    private void toggleIcon() {
        if (currentIcon.equals(hideIcon)) {
            currentIcon = showIcon;
            setPasswordVisiable(true);
        }else {
            currentIcon = hideIcon;
            setPasswordVisiable(false);
        }
        setCompoundDrawables(null,null,currentIcon,null);
    }

    private void setPasswordVisiable(boolean visiable){
        if (visiable) {
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else {
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
