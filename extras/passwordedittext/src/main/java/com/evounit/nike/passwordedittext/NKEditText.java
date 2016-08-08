package com.evounit.nike.passwordedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Author:yaocl
 * Created on 2016/8/8.
 */
public class NKEditText extends RelativeLayout implements TextWatcher{

    private Context mContext;
    private EditText mEditText;
    private ImageView labelIcon;
    private ImageView deleteIcon;
    private TextView labelLeft;
    private TextView labelTop;

    private Drawable labelIconDraw;
    private int labelIconSize;

    private CharSequence labelLeftText;
    private CharSequence labelTopText;
    private int labelSize;
    private int labelSpace;
    private int labelPosition;

    private Drawable deleteIconDraw;
    private int deleteIconSize;
    private int deleteIconSpace;

    private Drawable showIcon;
    private Drawable hideIcon;
    private int passwordIconSize;
    private Drawable currentIcon;

    private boolean isLabelLeft = false;
    private boolean isLabelTop = false;
    private boolean isLabelIcon = false;

    private int editTextWidth;
    private int editTextHeight;

    //EditText proper

    public NKEditText(Context context) {
        this(context,null);
    }

    public NKEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NKEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setupAttr(context,attrs,defStyleAttr);
        handleAttr();

    }

    public void setupAttr(Context context,AttributeSet attrs,int defStyleAttr) {
        TypedArray values = context.getTheme().obtainStyledAttributes(attrs,R.styleable.NKEditText,defStyleAttr,0);

        labelIconDraw = values.getDrawable(R.styleable.NKEditText_labelIcon);
        labelIconSize = (int) values.getDimension(R.styleable.NKEditText_labelIconSize,0f);
        isLabelIcon = (labelIconDraw == null);

        labelLeftText = values.getText(R.styleable.NKEditText_label);
        labelTopText = values.getText(R.styleable.NKEditText_label);
        labelSize = (int) values.getDimension(R.styleable.NKEditText_labelSize,0f);
        labelSpace = (int) values.getDimension(R.styleable.NKEditText_labelSpace,0f);
        labelPosition = values.getInt(R.styleable.NKEditText_labelPosition,0);
        isLabelLeft = (labelLeftText == null);
        isLabelTop = (labelTopText == null);

        deleteIconDraw = values.getDrawable(R.styleable.NKEditText_deleteIcon);
        deleteIconSize = (int) values.getDimension(R.styleable.NKEditText_deleteIconSize,0f);
        deleteIconSpace = (int) values.getDimension(R.styleable.NKEditText_deleteIconSpace,0f);

        showIcon = values.getDrawable(R.styleable.NKEditText_showIcon);
        hideIcon = values.getDrawable(R.styleable.NKEditText_hideIcon);
        passwordIconSize = (int) values.getDimension(R.styleable.NKEditText_passwordIconSize,0f);

        editTextWidth = values.getDimension(R.styleable.NKEditText_editTextWidth,)

        values.recycle();
    }


    private void handleAttr() {
        if ((isLabelIcon&&isLabelLeft) || (isLabelIcon&&isLabelTop)|| (isLabelLeft&&isLabelTop)) {
            throw new IllegalArgumentException("can only set one label mode");
        }
        mEditText = new EditText(mContext);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
