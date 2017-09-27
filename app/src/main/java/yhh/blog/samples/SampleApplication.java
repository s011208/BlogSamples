package yhh.blog.samples;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
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
import yhh.commoncomponents.view.BaseApplication;
import yhh.runtimepermission.RunTimePermissionApplication;

public class SampleApplication extends Application {

    private BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = BaseApplicationFactory.create(this);

        if (mApplication != null) {
            mApplication.onCreate(SampleApplication.this);
        }
    }

    @Nullable
    public BaseApplication getBaseApplication() {
        return mApplication;
    }

    private static class BaseApplicationFactory {

        @Nullable
        private static BaseApplication create(@NonNull Context context) {
            final String processName = getProcessName(context);
            if (processName != null) {
                if ("yhh.blog.samples:mvp".equals(processName)) {
                    return new MVPApplication();
                } else if ("yhh.blog.samples:dagger2".equals(processName)) {
                    return new Dagger2Application();
                } else if ("yhh.blog.samples:butterknife".equals(processName)) {
                    return new ButterKnifeApplication();
                } else if ("yhh.blog.samples:okhttp".equals(processName)) {
                    return new OkHttpApplication();
                } else if ("yhh.blog.samples:rxjava".equals(processName)) {
                    return new RxJavaApplication();
                } else if ("yhh.blog.samples:gson".equals(processName)) {
                    return new GsonApplication();
                } else if ("yhh.blog.samples:sync".equals(processName)) {
                    return new SyncApplication();
                } else if ("yhh.blog.samples:leak_canary".equals(processName)) {
                    return new LeakCanaryApplication();
                } else if ("yhh.blog.samples:android".equals(processName)) {
                    return new AndroidApplication();
                } else if ("yhh.blog.samples:runtime_permission".equals(processName)) {
                    return new RunTimePermissionApplication();
                }
            }
            return null;
        }

        @Nullable
        private static String getProcessName(@NonNull Context context) {
            int pid = android.os.Process.myPid();
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
                if (processInfo.pid == pid) {
                    return processInfo.processName;
                }
            }
            return null;
        }
    }
}
