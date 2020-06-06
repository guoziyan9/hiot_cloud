package com.huatec.hiot_cloud.ui.ChangeEmailupdate;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;


public class ChangeUserEmailPresenter extends BasePresenter<ChangeUserEmailView> {

    /**
     * 网络层封装类  作用：调用service方法来实现登录
     */
    @Inject
    DataManager dataManager;

    @Inject
    public ChangeUserEmailPresenter() {

    }

    /**
     * 修改邮箱
     *
     * @param email
     */
    public void changeEmail(String email) {
        subscrib( dataManager.changeEmail( email ), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getData() )) {
                    String newEmail = resultBase.getData();
                    getView().showMessage( "修改成功，新邮箱：" + newEmail );
                    getView().changeuseremailSucc();
                }
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }
            }

        } );
    }
}
