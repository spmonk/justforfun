package com.evounic.nike.nikedialog;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Author:yaocl
 * Created on 2016/8/4.
 */
public class DialogHelper {

    /**
     * 根据builder来判断当前使用哪种布局方式
     * @param builder
     * @return
     */
    public static int selectContentView(NKDialog.Builder builder) {
        return R.layout.dialog_base;
    }


    /**
     * 建立布局并设置显示内容和是否显示
     * @param nkDialog
     */
    public static void setupView(NKDialog nkDialog) {
        NKDialog.Builder builder = nkDialog.mBuilder;

        //find views
        nkDialog.title = (TextView) nkDialog.rootView.findViewById(R.id.title);
        nkDialog.titleView = nkDialog.rootView.findViewById(R.id.titleFrame);
        nkDialog.content = (TextView) nkDialog.rootView.findViewById(R.id.content);
        nkDialog.positiveButton = (Button) nkDialog.rootView.findViewById(R.id.buttonDefaultPositive);
        nkDialog.negativeButton = (Button) nkDialog.rootView.findViewById(R.id.buttonDefaultNegative);


        //setUp values
        if (nkDialog.title != null) {
            if (builder.title != null) {
                nkDialog.title.setText(builder.title);
                nkDialog.titleView.setVisibility(View.VISIBLE);
            } else {
                nkDialog.titleView.setVisibility(View.GONE);
            }
        }

        if (nkDialog.content != null) {
            if (builder.content != null) {
                nkDialog.content.setText(builder.content);
                nkDialog.content.setVisibility(View.VISIBLE);
            }else {
                nkDialog.content.setVisibility(View.GONE);
            }
        }

        //action btn
        nkDialog.positiveButton.setVisibility(builder.positiveText == null?View.GONE:View.VISIBLE);
        nkDialog.negativeButton.setVisibility(builder.negativeText == null?View.GONE:View.VISIBLE);

        nkDialog.positiveButton.setText(builder.positiveText);
        nkDialog.positiveButton.setBackground(nkDialog.getBtnSelector(DialogAction.POSITIVE));
        nkDialog.positiveButton.setEnabled(builder.positiveEnable);
        nkDialog.negativeButton.setText(builder.negativeText);
        nkDialog.negativeButton.setBackground(nkDialog.getBtnSelector(DialogAction.NEGATIVE));


    }
}
