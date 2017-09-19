package yhh.blog.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yhh.blog.samples.butterknife.ButterKnifeMainActivity;
import yhh.blog.samples.dagger2.Dagger2Activity;
import yhh.blog.samples.mvp.view.MVPDemoActivity;
import yhh.blog.samples.okhttp.OkHttpActivity;
import yhh.blog.samples.rxjava.RxJavaActivity;

public class SampleListActivity extends AppCompatActivity {

    private static final int INDEX_MVP = 0;
    private static final int INDEX_DAGGER2 = 1;
    private static final int INDEX_BUTTERKNIFE = 2;
    private static final int INDEX_OKHTTP = 3;
    private static final int INDEX_RXJAVA = 4;
    private static final int INDEX_GSON = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        final List<String> items = new ArrayList<>();
        items.add(getString(R.string.activity_sample_list_mvp));
        items.add(getString(R.string.activity_sample_list_dagger2));
        items.add(getString(R.string.activity_sample_list_butterknife));
        items.add(getString(R.string.activity_sample_list_okhttp));
        items.add(getString(R.string.activity_sample_list_rxjava));
        items.add(getString(R.string.activity_sample_list_gson));
//        items.add(getString(R.string.activity_sample_list_mockito));
//        items.add(getString(R.string.activity_sample_list_volley));

//        items.add(getString(R.string.activity_sample_list_async));
//        items.add(getString(R.string.activity_sample_list_jackson));
//        items.add(getString(R.string.activity_sample_list_retrofit));
//        items.add(getString(R.string.activity_sample_list_permission));
//        items.add(getString(R.string.activity_sample_list_ixjava));


        ListView listView = (ListView) findViewById(R.id.sample_list);
        listView.setAdapter(new ArrayAdapter<>(SampleListActivity.this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == INDEX_MVP) {
                    startActivity(new Intent(SampleListActivity.this, MVPDemoActivity.class));
                } else if (i == INDEX_DAGGER2) {
                    startActivity(new Intent(SampleListActivity.this, Dagger2Activity.class));
                } else if (i == INDEX_BUTTERKNIFE) {
                    startActivity(new Intent(SampleListActivity.this, ButterKnifeMainActivity.class));
                } else if (i == INDEX_OKHTTP) {
                    startActivity(new Intent(SampleListActivity.this, OkHttpActivity.class));
                } else if (i == INDEX_RXJAVA) {
                    startActivity(new Intent(SampleListActivity.this, RxJavaActivity.class));
                } else if (i == INDEX_GSON) {
                    startActivity(new Intent(SampleListActivity.this, RxJavaActivity.class));
                }
            }
        });
    }
}
