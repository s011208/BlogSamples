package yhh.blog.samples.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yhh.blog.samples.R;

/**
 * https://stackoverflow.com/questions/42757924/what-is-the-difference-between-observable-completable-and-single-in-rxjava
 * https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#backpressure
 * https://zouzhberk.github.io/rxjava-study/
 */
public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";
    private static final boolean DEBUG = true;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2);
        mTextView = (TextView) findViewById(R.id.status_text);
        final OkHttpClient client = new OkHttpClient();
        final Single<List<JSONObject>> queryObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Response response = client.newCall(new Request.Builder().url("https://hacker-news.firebaseio.com/v0/topstories.json").build()).execute();
                emitter.onNext(response.body().string());
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(mTextView.getText() + System.lineSeparator() + "get top stories from hacker news");
                    }
                });
            }
        }).map(new Function<String, List<Integer>>() {
            @Override
            public List<Integer> apply(@NonNull String s) throws Exception {
                JSONArray jsonArray = new JSONArray(s);
                List<Integer> rtn = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); ++i) {
                    rtn.add(jsonArray.getInt(i));
                }
                return rtn;
            }
        }).doOnNext(new Consumer<List<Integer>>() {
            @Override
            public void accept(final List<Integer> integers) throws Exception {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(mTextView.getText() + System.lineSeparator() + "get " + integers.size() + " stories' id");
                    }
                });
            }
        }).flatMap(new Function<List<Integer>, ObservableSource<JSONObject>>() {
            @Override
            public ObservableSource<JSONObject> apply(@NonNull List<Integer> integerArray) throws Exception {
                ArrayList<JSONObject> data = new ArrayList<>();
                int counter = 0;
                for (int i : integerArray) {
                    Response response = client.newCall(new Request.Builder().url("https://hacker-news.firebaseio.com/v0/item/" + i + ".json").build()).execute();
                    data.add(new JSONObject(response.body().string()));
                    ++counter;
                    if (counter >= 30) break;
                }
                return Observable.fromIterable(data);
            }
        }).doOnNext(new Consumer<JSONObject>() {
            @Override
            public void accept(final JSONObject jsonObject) throws Exception {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mTextView.setText(mTextView.getText() + System.lineSeparator() + "loading story id " + jsonObject.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).toList();

        Disposable subscribe = queryObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<JSONObject>>() {

                    @Override
                    public void accept(List<JSONObject> jsonObjects) throws Exception {
                        if (DEBUG) {
                            for (JSONObject jsonObject : jsonObjects) {
                                Log.v(TAG, "accept, item: " + jsonObject.toString());
                            }
                        }
                        mTextView.setText(mTextView.getText() + System.lineSeparator() + "load " + jsonObjects.size() + " stories");
                    }
                });
        mDisposable.add(subscribe);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
