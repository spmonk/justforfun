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
    private Drawable labelIcon;
    private Drawable labelIconFocused;
    private Drawable deleteIcon;
    private int labelIconSpace;

    private int colorAccent;//当前主题的colorAccent
    private int labelIconSize;
    private int actionIconSize;

    private Drawable currentIcon;//当前的显示的图标

    private boolean isDeleteMode = false;

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
        deleteIcon = typedArray.getDrawable(R.styleable.NKPasswordEditText_deleteIcon);
        actionIconSize = (int) typedArray.getDimension(R.styleable.NKPasswordEditText_actionIconSize,getTextSize());

        labelIcon = typedArray.getDrawable(R.styleable.NKPasswordEditText_labelIcon);
        labelIconSpace = (int) typedArray.getDimension(R.styleable.NKPasswordEditText_labelIconSpace,0f);
        labelIconSize = (int) typedArray.getDimension(R.styleable.NKPasswordEditText_labelIconSize,getTextSize());

        typedArray = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
        colorAccent = typedArray.getColor(0,0xFF00FF);
        typedArray.recycle();

        if ((showIcon == null && hideIcon != null)||(showIcon != null && hideIcon == null)) {
            currentIcon = null;
            throw new IllegalArgumentException("需要同时设置显示图标和隐藏图标");
        }
        if ((deleteIcon != null)&&(showIcon !=null || hideIcon != null)) {
            currentIcon = null;
            throw new IllegalArgumentException("只能设置一种模式");
        }

        /*//默认图标
        if (showIcon == null ) {
            showIcon = getResources().getDrawable(R.drawable.wifi_eye_kejian);
        }
        if (hideIcon == null) {
            hideIcon = getResources().getDrawable(R.drawable.wifi_eye);
        }*/
        if (showIcon != null && hideIcon != null) {
            showIcon.setBounds(0, 0, actionIconSize, actionIconSize);
            hideIcon.setBounds(0, 0, actionIconSize, actionIconSize);
            currentIcon = hideIcon;
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if (labelIcon != null) {
            labelIcon.setBounds(0, 0, labelIconSize, labelIconSize);
            setCompoundDrawables(labelIcon,null,null,null);
            setCompoundDrawablePadding(labelIconSpace);
        }

        if (deleteIcon != null) {
            isDeleteMode = true;
            deleteIcon.setBounds(0, 0, actionIconSize, actionIconSize);
            currentIcon = deleteIcon;
        }
        //DrawableCompat.setTint(showIcon,colorAccent);
        //DrawableCompat.setTint(hideIcon,colorAccent);
    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
       if (text.length() >0) {
            setCompoundDrawables(labelIcon,null,currentIcon,null);
        }else {
            setCompoundDrawables(labelIcon,null,null,null);
           if (isDeleteMode) {
               currentIcon = deleteIcon;
               return;
           }
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
                if (isDeleteMode) {
                    clearText();
                }else {
                    toggleIcon();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void clearText() {
        this.setText("");
    }

    private void toggleIcon() {
        if (currentIcon.equals(hideIcon)) {
            currentIcon = showIcon;
            setPasswordVisiable(true);
        }else {
            currentIcon = hideIcon;
            setPasswordVisiable(false);
        }
        setCompoundDrawables(labelIcon,null,currentIcon,null);
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

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            setCompoundDrawables(labelIcon,null,currentIcon,null);
        }
    }
}
