package com.evounic.nike.nikedialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * Author:yaocl
 * Created on 2016/8/3.
 */
public class NKDialogBase extends Dialog{

    protected View rootView;

    public NKDialogBase(Context context) {
        this(context,R.style.NKCustomDialog);
    }

    @Override
    public View findViewById(int id){
        return rootView.findViewById(id);
    }

    public NKDialogBase(Context context, int themeResId) {
        super(context, R.style.NKCustomDialog);
    }

    protected NKDialogBase(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
