package yhh.blog.samples;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import yhh.blog.samples.mvp.MVPApplication;

public class SampleApplication extends Application {

    private BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        final String processName = getProcessName();
        if (processName != null) {
            if (processName.endsWith(":mvp")) {
                mApplication = new MVPApplication();
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
}
