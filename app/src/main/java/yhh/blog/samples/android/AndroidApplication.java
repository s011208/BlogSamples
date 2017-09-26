package yhh.blog.samples.android;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import yhh.commoncomponents.view.BaseApplication;

public class AndroidApplication implements BaseApplication {
    @Override
    public void onCreate(Application application) {
        LeakCanary.install(application);
    }
}
