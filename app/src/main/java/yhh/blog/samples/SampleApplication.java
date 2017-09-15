package yhh.blog.samples;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import yhh.blog.samples.butterknife.ButterKnifeApplication;
import yhh.blog.samples.dagger2.Dagger2Application;
import yhh.blog.samples.mvp.MVPApplication;

public class SampleApplication extends Application {

    private BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        final String processName = getProcessName();
        if (processName != null) {
            if ("yhh.blog.samples:mvp".equals(processName)) {
                mApplication = new MVPApplication();
            } else if ("yhh.blog.samples:dagger2".equals(processName)) {
                mApplication = new Dagger2Application();
            } else if ("yhh.blog.samples:butterknife".equals(processName)) {
                mApplication = new ButterKnifeApplication();
            }
        }

        if (mApplication != null) {
            mApplication.onCreate(SampleApplication.this);
        }
    }

    @Nullable
    private String getProcessName() {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return null;
    }

    @Nullable
    public BaseApplication getBaseApplication() {
        return mApplication;
    }
}
