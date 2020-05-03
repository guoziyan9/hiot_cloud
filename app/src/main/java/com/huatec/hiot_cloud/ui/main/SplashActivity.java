package com.huatec.hiot_cloud.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;


import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static final int HANDLER_MSC_OPEN_NEW = 1;
private Handler handler = new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage( msg );
        boolean hasLogin = true;
        if (msg.what == HANDLER_MSC_OPEN_NEW) {
            //根据登录状态给hasLogin赋值

            //打开列表界面或者登录界面

            if (hasLogin) {
                //打开列表界面
                Intent intent = new Intent( SplashActivity.this, MainActivity.class );
                startActivity( intent );
                finish();

            } else {
                //打开登录界面
               Intent intent = new Intent( SplashActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
            }

        }


    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        //设置定时器
        new Timer().schedule( new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage( HANDLER_MSC_OPEN_NEW );
            }
        } ,3000);
    }
}
