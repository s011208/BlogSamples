package yhh.blog.samples.android.asynctask.leak;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.ref.WeakReference;

public class AsyncTaskLeakActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskLeakActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MyAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<Activity> mActivity;

        MyAsyncTask(Activity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Activity activity = mActivity.get();
            if (activity == null) {
                Log.w(TAG, "activity has gone");
                return null;
            }
            Log.d(TAG, activity.getClass().getName());
            return null;
        }
    }
}
