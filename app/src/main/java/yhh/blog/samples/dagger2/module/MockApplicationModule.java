package yhh.blog.samples.dagger2.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yhh.blog.samples.dagger2.User;

@Module
public class MockApplicationModule {

    public MockApplicationModule(Application application) {
    }

    @Singleton
    @Provides
    User provideUser() {
        return new User("Mock s011208", "1234");
    }
}
