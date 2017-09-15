package yhh.blog.samples.butterknife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import yhh.blog.samples.R;
import yhh.blog.samples.butterknife.samples.BindActivityViewActivity;
import yhh.blog.samples.butterknife.samples.BindFragmentActivity;
import yhh.blog.samples.butterknife.samples.BindXMLResourceActivity;

public class ButterKnifeMainActivity extends AppCompatActivity {

    @BindView(R.id.sample_list)
    ListView mButterKnifeSampleList;

    @BindString(R.string.butter_knife_main_activity_bind_activity_view)
    String mBindActivityView;

    @BindArray(R.array.butter_knife_main_activity_samples)
    String[] mSamples;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_main);
        ButterKnife.bind(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mSamples);
        mButterKnifeSampleList.setAdapter(arrayAdapter);

    }

    @OnItemClick(R.id.sample_list)
    void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, BindActivityViewActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, BindXMLResourceActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BindFragmentActivity.class));
                break;
        }
    }
}
