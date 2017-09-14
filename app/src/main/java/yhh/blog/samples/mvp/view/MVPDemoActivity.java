package yhh.blog.samples.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import yhh.blog.samples.R;
import yhh.blog.samples.mvp.contract.LoginContract;
import yhh.blog.samples.mvp.presenter.LoginPresenter;

public class MVPDemoActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private EditText mUserName, mPassword;
    private Button mLogin, mClear;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_demo);
        mPresenter = new LoginPresenter(MVPDemoActivity.this);

        mUserName = (EditText) findViewById(R.id.user);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mClear = (Button) findViewById(R.id.clear);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPresenter.onLoginButtonEnterText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPresenter.onLoginButtonEnterText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.login(mUserName.getText().toString(), mPassword.getText().toString());
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.clear();
            }
        });
    }

    @Override
    public void showLoadingView() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearUserAndPasswordText() {
        mUserName.setText("");
        mPassword.setText("");
    }

    @Override
    public void disableButtons() {
        mLogin.setEnabled(false);
        mClear.setEnabled(false);
    }

    @Override
    public void enableButtons() {
        mLogin.setEnabled(true);
        mClear.setEnabled(true);
    }

    @Override
    public int getUserButtonTextLength() {
        return mUserName.getText().length();
    }

    @Override
    public int getPasswordButtonTextLength() {
        return mPassword.getText().length();
    }

    @Override
    public void showToast(@StringRes int toastResource) {
        Toast.makeText(MVPDemoActivity.this, toastResource, Toast.LENGTH_LONG).show();
    }
}
