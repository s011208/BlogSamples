package yhh.blog.samples.dagger2.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import yhh.blog.samples.dagger2.Dagger2Application;
import yhh.blog.samples.dagger2.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Dagger2Application application);
}
