package yhh.blog.samples.dagger2;

import android.app.Application;

import javax.inject.Inject;

import yhh.blog.samples.BaseApplication;
import yhh.blog.samples.dagger2.component.ApplicationComponent;
import yhh.blog.samples.dagger2.component.DaggerApplicationComponent;
import yhh.blog.samples.dagger2.module.ApplicationModule;

public class Dagger2Application implements BaseApplication {
    private Application mApplication;
    private ApplicationComponent mApplicationComponent;

    @Inject
    User mUser;

    @Override
    public void onCreate(Application application) {
        mApplication = application;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(mApplication)).build();
        mApplicationComponent.inject(Dagger2Application.this);
    }

    public synchronized ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public User getUser() {
        return mUser;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
        mApplicationComponent.inject(this);
    }
}
