package com.huatec.hiot_cloud.ui.ChangePassword;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 修改密码presenter类
 */
class ChangeUserPasswordPresenter extends BasePresenter<ChangeUserPasswordView> {
    /**
     * 网络层封装类  作用：调用service方法来实现登录
     */
    @Inject
    DataManager dataManager;

    @Inject
    public ChangeUserPasswordPresenter() {

    }


    /**
     * 修改密码
     *
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     */
    public void GPassword(String oldpassword, String newpassword, String confirmpassword) {
        subscrib( dataManager.updatePassword( oldpassword, newpassword, confirmpassword ), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getData() )) {
                    String confirmpassword = resultBase.getData();
                    getView().showMessage( "修改成功" );
                    getView().changeuserpasswordSucc();
                }
                if (resultBase != null && !TextUtils.isEmpty( resultBase.getMsg() )) {
                    getView().showMessage( resultBase.getMsg() );
                }

            }
        } );

    }


}
