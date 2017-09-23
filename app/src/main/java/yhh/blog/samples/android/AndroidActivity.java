package yhh.blog.samples.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import yhh.blog.samples.R;
import yhh.blog.samples.android.asynctask.leak.AsyncTaskLeakActivity;

public class AndroidActivity extends AppCompatActivity {
    private static final int INDEX_ANDROID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        String[] dataList = new String[]{
                getString(R.string.activity_android_asynctask_leak)
        };

        ListView listView = (ListView) findViewById(R.id.android_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case INDEX_ANDROID:
                        startActivity(new Intent(AndroidActivity.this, AsyncTaskLeakActivity.class));
                        break;
                }
            }
        });
    }
}
