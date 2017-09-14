package yhh.blog.samples.mvp.contract;

import android.os.Handler;
import android.support.annotation.StringRes;

public interface LoginContract {
    interface View {
        void showLoadingView();

        void hideLoadingView();

        void clearUserAndPasswordText();

        void disableButtons();

        void enableButtons();

        int getUserButtonTextLength();

        int getPasswordButtonTextLength();

        void showToast(@StringRes int toastResource);
    }

    interface Presenter {
        void login(String user, String password);

        void clear();

        void onLoginButtonEnterText();
    }

    interface Model {
        void checkUserValid(String user, String password, Handler.Callback callback);
    }
}
