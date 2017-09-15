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

public class SampleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        final List<String> items = new ArrayList<>();
        items.add(getString(R.string.activity_sample_list_mvp));
        items.add(getString(R.string.activity_sample_list_dagger2));
        items.add(getString(R.string.activity_sample_list_mockito));
        items.add(getString(R.string.activity_sample_list_butterknife));
        items.add(getString(R.string.activity_sample_list_okhttp));

        ListView listView = (ListView) findViewById(R.id.sample_list);
        listView.setAdapter(new ArrayAdapter<>(SampleListActivity.this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    startActivity(new Intent(SampleListActivity.this, MVPDemoActivity.class));
                } else if (i == 1) {
                    startActivity(new Intent(SampleListActivity.this, Dagger2Activity.class));
                } else if (i == 2) {
                    startActivity(new Intent(SampleListActivity.this, ButterKnifeMainActivity.class));
                }
            }
        });
    }
}
