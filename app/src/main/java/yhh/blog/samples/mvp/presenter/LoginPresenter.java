package yhh.blog.samples.mvp.presenter;

import android.os.Handler;
import android.os.Message;

import yhh.blog.samples.R;
import yhh.blog.samples.mvp.contract.LoginContract;
import yhh.blog.samples.mvp.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    @Override
    public void login(String user, String password) {
        mLoginView.showLoadingView();
        mLoginModel.checkUserValid(user, password, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                boolean result = message.what == 0;
                if (result) {
                    mLoginView.showToast(R.string.mvp_demo_activity_login_success);
                } else {
                    mLoginView.showToast(R.string.mvp_demo_activity_login_fail);
                }
                mLoginView.hideLoadingView();
                return false;
            }
        });
    }

    @Override
    public void clear() {
        mLoginView.clearUserAndPasswordText();
    }

    @Override
    public void onLoginButtonEnterText() {
        if (mLoginView.getPasswordButtonTextLength() == 0 && mLoginView.getUserButtonTextLength() == 0) {
            mLoginView.disableButtons();
        } else {
            mLoginView.enableButtons();
        }
    }
}
