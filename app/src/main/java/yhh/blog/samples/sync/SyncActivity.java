package yhh.blog.samples.sync;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.CountDownLatch;

import yhh.blog.samples.R;

public class SyncActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SyncActivity";

    private Button mTestMethod, mTestStaticMethod, mTestThis, mTestObject;
    private Button mTestSyncMethod, mTestSyncStaticMethod, mTestSyncThis, mTestSyncObject;
    private TextView mStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        mTestMethod = (Button) findViewById(R.id.test_method);
        mTestStaticMethod = (Button) findViewById(R.id.test_static_method);
        mTestThis = (Button) findViewById(R.id.test_this);
        mTestObject = (Button) findViewById(R.id.test_object);

        mTestSyncMethod = (Button) findViewById(R.id.test_sync_method);
        mTestSyncStaticMethod = (Button) findViewById(R.id.test_sync_static_method);
        mTestSyncThis = (Button) findViewById(R.id.test_sync_this);
        mTestSyncObject = (Button) findViewById(R.id.test_sync_object);

        mTestMethod.setOnClickListener(this);
        mTestStaticMethod.setOnClickListener(this);
        mTestThis.setOnClickListener(this);
        mTestObject.setOnClickListener(this);

        mTestSyncMethod.setOnClickListener(this);
        mTestSyncStaticMethod.setOnClickListener(this);
        mTestSyncThis.setOnClickListener(this);
        mTestSyncObject.setOnClickListener(this);

        mStatus = (TextView) findViewById(R.id.status);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_method:
                runMethodAsynchronized();
                break;
            case R.id.test_static_method:
                runStaticMethodAsynchronized();
                break;
            case R.id.test_this:
                break;
            case R.id.test_object:
                break;
            case R.id.test_sync_method:
                runMethodSynchronized();
                break;
            case R.id.test_sync_static_method:
                runStaticMethodSynchronized();
                break;
            case R.id.test_sync_this:
                break;
            case R.id.test_sync_object:
                break;
        }
    }

    private void runMethodAsynchronized() {
        mStatus.setText("");

        final SyncObject syncObject = new SyncObject();

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncObject.instanceAdd("First: ");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncObject.instanceAdd("Second: ");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countDownLatch.getCount() != 0) ;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (String s : syncObject.getMList()) {
                            sb.append(System.lineSeparator() + s);
                        }
                        Log.v(TAG, sb.toString());
                        mStatus.setText("");
                        mStatus.setText(sb.toString());
                    }
                });
            }
        }).start();
    }

    private void runMethodSynchronized() {
        mStatus.setText("");

        final SyncObject syncObject = new SyncObject();

        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncObject.synchronizedInstanceAdd("First: ");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncObject.synchronizedInstanceAdd("Second: ");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countDownLatch.getCount() != 0) ;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (String s : syncObject.getMList()) {
                            sb.append(System.lineSeparator() + s);
                        }
                        Log.v(TAG, sb.toString());
                        mStatus.setText("");
                        mStatus.setText(sb.toString());
                    }
                });
            }
        }).start();
    }

    private void runStaticMethodAsynchronized() {
        mStatus.setText("");

        SyncObject.clearList();
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncObject.add("First: ");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncObject.add("Second: ");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countDownLatch.getCount() != 0) ;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (String s : SyncObject.getSList()) {
                            sb.append(System.lineSeparator() + s);
                        }
                        Log.v(TAG, sb.toString());
                        mStatus.setText("");
                        mStatus.setText(sb.toString());
                    }
                });
            }
        }).start();
    }

    private void runStaticMethodSynchronized() {
        mStatus.setText("");

        SyncObject.clearList();
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncObject.synchronizedAdd("First: ");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncObject.synchronizedAdd("Second: ");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (countDownLatch.getCount() != 0) ;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (String s : SyncObject.getSList()) {
                            sb.append(System.lineSeparator() + s);
                        }
                        Log.v(TAG, sb.toString());
                        mStatus.setText("");
                        mStatus.setText(sb.toString());
                    }
                });
            }
        }).start();
    }
}
