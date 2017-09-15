package yhh.blog.samples.butterknife.samples;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yhh.blog.samples.R;

public class BindFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new SampleFragment()).commitAllowingStateLoss();
    }

    public static class SampleFragment extends Fragment {
        @BindView(R.id.fragment_text)
        TextView mFragmentText;

        private Unbinder mUnbinder;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rtn = inflater.inflate(R.layout.fragment_bind_fragment, container, false);
            mUnbinder = ButterKnife.bind(this, rtn);
            mFragmentText.setText("Success");

            return rtn;
        }

        @Override public void onDestroyView() {
            super.onDestroyView();
            mUnbinder.unbind();
        }
    }
}
