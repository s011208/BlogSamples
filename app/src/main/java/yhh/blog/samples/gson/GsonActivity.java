package yhh.blog.samples.gson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;


public class GsonActivity extends AppCompatActivity {
    private static final String JSON_DATA = "{\"by\":\"loeg\",\"descendants\":190,\"id\":15276926,\"kids\":[15277114,15277280,15277249,15277716,15277680,15277068,15277067,15277505,15277360,15277361,15277390,15277469,15278963,15277160,15277806,15277070,15277210,15277824,15278733,15277177,15277240,15278663,15277228,15277704,15277134,15277063,15277363,15277323,15277421,15278753],\"score\":410,\"time\":1505749772,\"title\":\"Equifax Stock Sales Are the Focus of U.S. Criminal Probe\",\"type\":\"story\",\"url\":\"https://www.bloomberg.com/news/articles/2017-09-18/equifax-stock-sales-said-to-be-focus-of-u-s-criminal-probe\"}";
    private static final String JSON_DATA_ALTERNATE = "{\"by_id\":\"loeg\",\"descendants\":190,\"id\":15276926,\"kids\":[15277114,15277280,15277249,15277716,15277680,15277068,15277067,15277505,15277360,15277361,15277390,15277469,15278963,15277160,15277806,15277070,15277210,15277824,15278733,15277177,15277240,15278663,15277228,15277704,15277134,15277063,15277363,15277323,15277421,15278753],\"score\":410,\"time\":1505749772,\"title\":\"Equifax Stock Sales Are the Focus of U.S. Criminal Probe\",\"type\":\"story\",\"url\":\"https://www.bloomberg.com/news/articles/2017-09-18/equifax-stock-sales-said-to-be-focus-of-u-s-criminal-probe\"}";

    private static final String TAG = "GsonActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        Log.v(TAG, String.valueOf(gson.toJson(gson.fromJson(JSON_DATA_ALTERNATE, User.class)).equals(gson.toJson(gson.fromJson(JSON_DATA, User.class)))));
    }
}
