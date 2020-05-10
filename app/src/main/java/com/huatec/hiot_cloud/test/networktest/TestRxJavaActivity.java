package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.NetService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxJavaActivity extends AppCompatActivity {

    private static final String TAG = "TestRxJavaActivity";
    private Retrofit retrofit;

    private NetService service;
    private EditText etToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_test_rx_java );


        //editText
        etToken = findViewById( R.id.et_rxjava_token );

        //创建retrofit
        createRetrofit();

        //登录
        Button btnLogin = findViewById( R.id.btn_rxjava_login );
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login("jiandan","jiandan123");
            }
        } );

        //用户信息
        Button btnUserInfo = findViewById( R.id.btn_rxjava_uerinfo );
        btnUserInfo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo(etToken.getText().toString());

            }
        } );

        //修改邮箱
        Button btnUpdateEmail = findViewById( R.id.btn_rxjava_update_email );
        btnUpdateEmail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("2bbd3f1e2b0148a2a3600d3160a280ec_2bab61d7beb2480fa88ee8f6a56a7558_use",
                        "wulian2@qq.com");

            }
        } );

        //注册
        Button btnRegister = findViewById( R.id.btn_rxjava_register );
        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        } );
    }

    /**
     * 用户注册
     * @param
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername( "gzy" );
        userBean.setEmail( "gzyemail@qq.com" );
        userBean.setPassword( "gzy123" );
        userBean.setUserType( "1" );
        service.register( userBean )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null){
                            UserBean userBean1 =resultBase.getData();
                            String str1 = String.format( "用户名：%s，email：%s，密码：%s，用户类型：%s",
                                    userBean1.getUsername(),userBean1.getEmail(),userBean1.getPassword(),userBean1.getUserType() );
                            Toast.makeText( TestRxJavaActivity.this, str1, Toast.LENGTH_SHORT ).show();
                        }
                        if(resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )){
                            Toast.makeText( TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        Observable<ResultBase<String>> observable = service.updateEmail( authorization,email );
        observable.observeOn( AndroidSchedulers.mainThread() ).subscribeOn( Schedulers.io() )
                .subscribe( new Observer<ResultBase<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<String> resultBase) {
                        if (resultBase !=null && resultBase.getData() != null){
                            String string = resultBase.getData();
                            String str = String.format( "email：%s", string);
                            Toast.makeText( TestRxJavaActivity.this, str, Toast.LENGTH_SHORT ).show();
                        }
                        if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )){
                            Toast.makeText( TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    /**
     * 获取用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.getUserInfo( authorization );
        observable.observeOn( AndroidSchedulers.mainThread() ).subscribeOn( Schedulers.io() )
                .subscribe( new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null){
                            UserBean userBean = resultBase.getData();
                            String str = String.format( "用户：%s，email：%s",
                                    userBean.getUsername(),userBean.getEmail() );
                            Toast.makeText( TestRxJavaActivity.this, str, Toast.LENGTH_SHORT ).show();
                        }
                        else if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )){
                            Toast.makeText( TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT ).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    /**
     * 登录
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        service.login( userName,password,"app" )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<LoginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            LoginResultDTO loginResult = resultBase.getData();
                            Log.d( TAG, "onNext: " + loginResult.getTokenValue() );
                            etToken.setText( loginResult.getTokenValue() );
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e( TAG, "onError: " + e.getMessage(), e );

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    /**
     * 创建retrofit
     */
    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl( TestRetrofitService.basUrl )
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();

        service = retrofit.create( NetService.class );
    }
}
