package yhh.blog.samples.mvp;

import android.app.Application;
import android.content.Context;

import yhh.blog.samples.BaseApplication;

public class MVPApplication implements BaseApplication {
    private Application mApplication;

    @Override
    public void onCreate(Application application) {
        mApplication = application;
    }

    @Override
    public Context getContext() {
        return mApplication;
    }

}