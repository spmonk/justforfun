package com.evounic.nike.nikedialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/**
 * Author:yaocl
 * Created on 2016/8/5.
 */
public class ResourceHelper {


    /**
     * 从主题定义的属性中解析相应的资源文件
     * @param context
     * @param attrId
     * @return
     */
    public static Drawable resolveDrawable(Context context, int attrId) {
        return resolveDrawable(context,attrId,null);
    }

    /**
     * 从主题定义的属性中解析相应的资源文件
     * @param context
     * @param attrId
     * @param fallback 默认值
     * @return
     */
    public static Drawable resolveDrawable(Context context, int attrId,Drawable fallback) {
        TypedArray value = context.getTheme().obtainStyledAttributes(new int[]{attrId});

        try {
            Drawable drawable = value.getDrawable(0);
            if (drawable == null&&fallback != null) {
                drawable = fallback;
            }
            return drawable;
        } finally {
            value.recycle();
        }
    }


}
