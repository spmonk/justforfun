package com.evounic.nike.nikedialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Author:yaocl
 * Created on 2016/8/3.
 * 自定义Dialog使用了Builder构造者模式，它的基本代码为
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
        super(builder.mContext);
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

        //show text
        protected CharSequence title;
        protected CharSequence content;
        protected CharSequence positiveText;
        protected CharSequence negativeText;

        //show color
        protected ColorStateList positiveColor;
        protected ColorStateList negativeColor;


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

        public Builder positiveColor(int color){
            this.positiveColor = color;
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
