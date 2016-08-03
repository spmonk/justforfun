package com.evounic.nike.nikedialog;

import android.content.Context;

/**
 * Author:yaocl
 * Created on 2016/8/3.
 */
public class NKDialog extends NKDialogBase{

    protected final Builder mBuilder;

    //protected 不让外部直接调用进行初始化，但允许子类继承,通过Builder来调用生成相应实例
    protected NKDialog(Builder builder) {
        super(builder.mContext,0);
        mBuilder = builder;
    }

    public static class Builder {
        protected final Context mContext;

        public Builder(Context context) {
            mContext = context;
        }
    }
}
