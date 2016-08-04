package com.example.testing.justforfun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.evounic.nike.nikedialog.NKDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNextPageClick(View view) {
//        startActivity(new Intent(this,SecondActivity.class));
        new NKDialog.Builder(this).title("Dialog Test").content("this is a message for test").positiveText("确定")
                .negativeText("取消").create().show();
    }
}
