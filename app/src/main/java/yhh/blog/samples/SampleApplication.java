package yhh.blog.samples;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import yhh.blog.samples.android.AndroidApplication;
import yhh.blog.samples.butterknife.ButterKnifeApplication;
import yhh.blog.samples.dagger2.Dagger2Application;
import yhh.blog.samples.gson.GsonApplication;
import yhh.blog.samples.leakcanary.LeakCanaryApplication;
import yhh.blog.samples.mvp.MVPApplication;
import yhh.blog.samples.okhttp.OkHttpApplication;
import yhh.blog.samples.rxjava.RxJavaApplication;
import yhh.blog.samples.sync.SyncApplication;

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
            } else if ("yhh.blog.samples:okhttp".equals(processName)) {
                mApplication = new OkHttpApplication();
            } else if ("yhh.blog.samples:rxjava".equals(processName)) {
                mApplication = new RxJavaApplication();
            } else if ("yhh.blog.samples:gson".equals(processName)) {
                mApplication = new GsonApplication();
            } else if ("yhh.blog.samples:sync".equals(processName)) {
                mApplication = new SyncApplication();
            } else if ("yhh.blog.samples:leak_canary".equals(processName)) {
                mApplication = new LeakCanaryApplication();
            } else if ("yhh.blog.samples:android".equals(processName)) {
                mApplication = new AndroidApplication();
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
