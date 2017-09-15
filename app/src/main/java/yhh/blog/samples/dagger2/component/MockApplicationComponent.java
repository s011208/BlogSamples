package yhh.blog.samples.dagger2.component;

import javax.inject.Singleton;

import dagger.Component;
import yhh.blog.samples.dagger2.module.MockApplicationModule;

@Singleton
@Component(modules = MockApplicationModule.class)
public interface MockApplicationComponent extends ApplicationComponent {
}
