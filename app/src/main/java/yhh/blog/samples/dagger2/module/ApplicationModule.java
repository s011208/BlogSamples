package yhh.blog.samples.dagger2.module;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yhh.blog.samples.dagger2.User;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule() {
    }

    @Inject
    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    User provideUser() {
        return new User("s011208", "0000");
    }
}
