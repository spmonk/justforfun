package com.evounic.nike.nikedialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Author:yaocl
 * Created on 2016/8/3.
 * 自定义Dialog使用了Builder构造者模式
 *
 */
public class NKDialog extends NKDialogBase{

    protected final Builder mBuilder;

    //title
    protected TextView title;
    protected View titleView;

    //content
    protected TextView content;
    protected Button positiveButton;
    protected Button negativeButton;

    //protected 不让外部直接调用进行初始化，但允许子类继承,通过Builder来调用生成相应实例
    protected NKDialog(Builder builder) {
        super(builder.mContext,R.style.Default);
        mBuilder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        installContent();
    }

    private void installContent() {
        int layoutId = DialogHelper.selectContentView(mBuilder);
        rootView = LayoutInflater.from(mBuilder.mContext).inflate(layoutId, null);
        setContentView(rootView);
        DialogHelper.setupView(this);
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public void setPositiveButtonEnable(boolean enable){
        positiveButton.setEnabled(enable);
    }

    /**
     * 获取相应按钮的selector
     * @param action
     * @return
     */
    public Drawable getBtnSelector(DialogAction action){
        switch (action) {
            default: POSITIVE:
                if (mBuilder.positiveSelector != 0) {//使用者设置了selector
                   return ResourcesCompat.getDrawable(mBuilder.mContext.getResources()
                           ,mBuilder.positiveSelector,null);
                }
                Drawable positiveSelector = ResourceHelper.resolveDrawable(mBuilder.mContext
                        , R.attr.action_btn_positive_selector);//从默认的主题设置中获取默认的selector
                if (positiveSelector != null) {
                    return positiveSelector;
                }
                positiveSelector = ResourceHelper.resolveDrawable(getContext()
                        ,R.attr.action_btn_positive_selector);//从dialog默认的context获取默认的selector
                return positiveSelector;//return的代替了break
            case NEGATIVE:
                if (mBuilder.negativeSelector !=0) {
                    return ResourcesCompat.getDrawable(mBuilder.mContext.getResources()
                            ,mBuilder.negativeSelector,null);
                }
                Drawable negativeSelector = ResourceHelper.resolveDrawable(mBuilder.mContext
                        , R.attr.action_btn_negative_selector);
                if (negativeSelector != null) {
                    return negativeSelector;
                }
                negativeSelector = ResourceHelper.resolveDrawable(getContext()
                        ,R.attr.action_btn_negative_selector);
                return negativeSelector;
        }
    }

    @UiThread
    public void show(){
        // TODO: 2016/8/3 add show dialog exception
            super.show();
    }


    /**
     * NKDialog Builder
     */
    public static class Builder {
        protected final Context mContext;

        //theme
        //protected int theme;//设置Dialog使用的主题

        //show text
        protected CharSequence title;
        protected CharSequence content;
        protected CharSequence positiveText;
        protected CharSequence negativeText;

        //action Text color
        protected ColorStateList positiveColor;
        protected ColorStateList negativeColor;

        //acton Button selector
        protected int positiveSelector;
        protected int negativeSelector;

        //acton Button enable
        protected boolean positiveEnable = true;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder title(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder title(int resId){
            return title(mContext.getText(resId));
        }

        public Builder content(CharSequence content){
            this.content = content;
            return this;
        }

        public Builder content(int resId){
            return content(mContext.getText(resId));
        }

        public Builder positiveText(CharSequence positiveText){
            this.positiveText = positiveText;
            return this;
        }

        public Builder positiveText(int resId){
            return positiveText(mContext.getText(resId));
        }

        public Builder negativeText(CharSequence negativeText){
            this.negativeText = negativeText;
            return this;
        }

        public Builder negativeText(int resId){
            return negativeText(mContext.getText(resId));
        }

        public Builder btnSelector(int drawableId, DialogAction action){
            switch (action) {
                case POSITIVE:
                    positiveSelector = drawableId;
                    break;
                case NEGATIVE:
                    negativeSelector = drawableId;
                    break;
            }
            return this;
        }

        public Builder positiveEnable(boolean enable){
            this.positiveEnable = enable;
            return this;
        }


        /**
         * 创建Dialog
         * @return
         */
        public NKDialog create(){
            return new NKDialog(this);
        }

        /**
         * 新创建一个Dialog并显示
         * @return
         */
        public NKDialog show(){
            NKDialog dialog = new NKDialog(this);
            dialog.show();
            return dialog;
        }


    }
}
