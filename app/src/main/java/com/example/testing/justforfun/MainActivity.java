package com.example.testing.justforfun;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.evounic.nike.nikedialog.NKDialog;

public class MainActivity extends AppCompatActivity {

    private NKDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNextPageClick(View view) {
//        startActivity(new Intent(this,SecondActivity.class));
        mDialog =  new NKDialog.Builder(this).title("Dialog Test").content("this is a message for test sdfsdfasdfads sdfasdfasdfasdfasd asdfasdfasd").positiveText("确定")
                .negativeText("取消").positiveEnable(false).create();
        mDialog.show();
        //测试在外部是否可以控制dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDialog.setPositiveButtonEnable(true);
            }
        },3000);
    }

    public void onDialogClick(View view) {
    }

    public void onToggleEnable(View view) {

    }
}
