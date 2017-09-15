package yhh.blog.samples.butterknife.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yhh.blog.samples.R;

public class BindActivityViewActivity extends AppCompatActivity {

    @BindView(R.id.butter_knife_text)
    TextView mButterKnifeText;

    @BindView(R.id.edit_text)
    EditText mInput;

    @BindView(R.id.change_input_state_button)
    Button mChangeInputStateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_activity_view);
        ButterKnife.bind(this);
        mInput.setText("Success");
    }

    @OnClick(R.id.change_input_state_button)
    void onClick() {
        Toast.makeText(this, "onClick", Toast.LENGTH_LONG).show();
    }
}
