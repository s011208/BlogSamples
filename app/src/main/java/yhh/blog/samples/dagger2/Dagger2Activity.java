package yhh.blog.samples.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import yhh.blog.samples.R;
import yhh.blog.samples.SampleApplication;
import yhh.blog.samples.dagger2.component.DaggerApplicationComponent;
import yhh.blog.samples.dagger2.component.DaggerMockApplicationComponent;
import yhh.blog.samples.dagger2.module.ApplicationModule;
import yhh.blog.samples.dagger2.module.MockApplicationModule;

public class Dagger2Activity extends AppCompatActivity {

    private TextView mUserName, mPassword;

    private TextView mUserNameAfter, mPasswordAfter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        User user = getUserFromApplication();

        mUserName = (TextView) findViewById(R.id.user_name);
        mPassword = (TextView) findViewById(R.id.password);

        mUserName.setText(getString(R.string.dagger2_activity_username, user.getUserName()));
        mPassword.setText(getString(R.string.dagger2_activity_password, user.getPassword()));

        ((Dagger2Application) ((SampleApplication) getApplication()).getBaseApplication())
                .setApplicationComponent(DaggerMockApplicationComponent.builder()
                        .mockApplicationModule(new MockApplicationModule(getApplication())).build());

        user = getUserFromApplication();

        mUserNameAfter = (TextView) findViewById(R.id.user_name_after);
        mPasswordAfter = (TextView) findViewById(R.id.password_after);

        mUserNameAfter.setText(getString(R.string.dagger2_activity_username, user.getUserName()));
        mPasswordAfter.setText(getString(R.string.dagger2_activity_password, user.getPassword()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((Dagger2Application) ((SampleApplication) getApplication()).getBaseApplication())
                .setApplicationComponent(DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(getApplication())).build());
    }

    private User getUserFromApplication() {
        return ((Dagger2Application) ((SampleApplication) getApplication()).getBaseApplication()).getUser();
    }
}
