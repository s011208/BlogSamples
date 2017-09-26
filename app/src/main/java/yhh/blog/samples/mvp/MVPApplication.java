package yhh.blog.samples.mvp;

import android.app.Application;

import yhh.commoncomponents.view.BaseApplication;

public class MVPApplication implements BaseApplication {
    private Application mApplication;

    @Override
    public void onCreate(Application application) {
        mApplication = application;
    }
}