package yhh.blog.samples.okhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yhh.blog.samples.R;

public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = "OkHttpActivity";
    private static final boolean DEBUG = true;

    private TextView mExecuteDirectlyResult, mExecuteWithCallbackResult;
    private Button mExecuteDirectly, mExecuteWithCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        mExecuteDirectly = (Button) findViewById(R.id.execute_directly);
        mExecuteWithCallback = (Button) findViewById(R.id.execute_with_callback);

        mExecuteDirectlyResult = (TextView) findViewById(R.id.execute_directly_result);
        mExecuteWithCallbackResult = (TextView) findViewById(R.id.execute_with_callback_result);

        mExecuteDirectly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExecuteDirectlyResult.setText(null);
                mExecuteDirectly.setEnabled(false);
                new LoadAsyncTask(OkHttpActivity.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        mExecuteWithCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExecuteWithCallback.setEnabled(false);
                mExecuteWithCallbackResult.setText(null);
                final OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url("https://hacker-news.firebaseio.com/v0/topstories.json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @WorkerThread
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        if (DEBUG) {
                            Log.v(TAG, "onFailure pid: " + android.os.Process.myPid() + ", tid: " + android.os.Process.myTid());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mExecuteWithCallback.setEnabled(true);
                                mExecuteWithCallbackResult.setText(e.toString());
                            }
                        });
                    }

                    @WorkerThread
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (DEBUG) {
                            Log.v(TAG, "onResponse pid: " + android.os.Process.myPid() + ", tid: " + android.os.Process.myTid());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mExecuteWithCallback.setEnabled(true);
                                try {
                                    mExecuteWithCallbackResult.setText(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    @MainThread
    private void updateExecuteDirectlyResult(String result) {
        mExecuteDirectlyResult.setText(result);
        mExecuteDirectly.setEnabled(true);
    }

    private static class LoadAsyncTask extends AsyncTask<Void, Void, String> {
        private final WeakReference<OkHttpActivity> mActivity;

        private LoadAsyncTask(OkHttpActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Nullable
        @Override
        protected String doInBackground(Void... voids) {
            final OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url("https://hacker-news.firebaseio.com/v0/topstories.json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            final OkHttpActivity activity = mActivity.get();
            if (activity == null) return;
            activity.updateExecuteDirectlyResult(result);
        }
    }
}
