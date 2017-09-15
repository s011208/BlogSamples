package yhh.blog.samples;

import android.app.Application;
import android.content.Context;

public interface BaseApplication {
    void onCreate(Application application);

    Context getContext();
}
