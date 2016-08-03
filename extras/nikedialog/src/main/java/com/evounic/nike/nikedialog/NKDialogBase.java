package com.evounic.nike.nikedialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Author:yaocl
 * Created on 2016/8/3.
 */
public class NKDialogBase extends Dialog{
    public NKDialogBase(Context context) {
        this(context,0);
    }

    public NKDialogBase(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NKDialogBase(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
