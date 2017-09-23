package yhh.blog.samples.leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import yhh.blog.samples.BaseApplication;

public class LeakCanaryApplication implements BaseApplication {
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate(Application application) {
        mRefWatcher = LeakCanary.install(application);
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }
}
