package yhh.blog.samples.butterknife.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindArray;
import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import yhh.blog.samples.R;


public class BindXMLResourceActivity extends AppCompatActivity {
    @BindView(R.id.bind_string_resource)
    TextView mBindStringResource;

    @BindView(R.id.bind_dimen_resource)
    TextView mBindDimenResource;

    @BindView(R.id.bind_string_array_resource)
    TextView mBindStringArrayResource;

    @BindString(R.string.butter_knife_activity_binding_string)
    String mBindingString;

    @BindDimen(R.dimen.butter_knife_activity_binding_dimen)
    float mBindingDimen;

    @BindArray(R.array.butter_knife_activity_binding_array)
    String[] mBindingArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_xml_resource);
        ButterKnife.bind(this);

        mBindStringResource.setText(mBindingString);
        mBindDimenResource.setText(String.valueOf(mBindingDimen));
        StringBuilder sb = new StringBuilder();
        for (String string : mBindingArray) {
            sb.append(string).append(System.lineSeparator());
        }
        mBindStringArrayResource.setText(sb.toString());
    }
}
