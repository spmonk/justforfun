package com.evounic.nike.nikedialog;

import android.content.Context;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
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
    protected ImageView titleIcon;
    protected TextView title;
    protected View titleView;

    //protected 不让外部直接调用进行初始化，但允许子类继承,通过Builder来调用生成相应实例
    protected NKDialog(Builder builder) {
        super(builder.mContext,0);
        mBuilder = builder;
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    @UiThread
    public void show(){
        // TODO: 2016/8/3 add show dialog exception
            super.show();
    }

    public static class Builder {
        protected final Context mContext;

        private CharSequence title;

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
