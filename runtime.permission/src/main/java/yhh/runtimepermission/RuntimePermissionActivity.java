package yhh.runtimepermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class RuntimePermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "PermissionActivity";

    private static final int REQUEST_READ_WRITE_PERMISSIONS = 1000;
    private static final int REQUEST_FINE_LOCATION_PERMISSION = 1001;

    private Button mReadWrite, mFineLocation, mStartSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);

        mReadWrite = (Button) findViewById(R.id.request_rw);
        mFineLocation = (Button) findViewById(R.id.request_fine_location);
        mStartSettings = (Button) findViewById(R.id.start_settings);

        mReadWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean hasPermission = EasyPermissions.hasPermissions(RuntimePermissionActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                Log.v(TAG, "hasPermission: " + hasPermission);

                if (hasPermission) {
                    Toast.makeText(RuntimePermissionActivity.this, R.string.activity_runtime_permission_already_get_permissions, Toast.LENGTH_LONG).show();
                } else {
                    EasyPermissions.requestPermissions(RuntimePermissionActivity.this,
                            getString(R.string.activity_runtime_permission_rational),
                            REQUEST_READ_WRITE_PERMISSIONS,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            }
        });

        mFineLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean hasPermission = EasyPermissions.hasPermissions(RuntimePermissionActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                Log.v(TAG, "hasPermission: " + hasPermission);

                if (hasPermission) {
                    Toast.makeText(RuntimePermissionActivity.this, R.string.activity_runtime_permission_already_get_permissions, Toast.LENGTH_LONG).show();
                } else {
                    EasyPermissions.requestPermissions(RuntimePermissionActivity.this,
                            getString(R.string.activity_runtime_permission_rational),
                            REQUEST_FINE_LOCATION_PERMISSION,
                            Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
        });

        mStartSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_READ_WRITE_PERMISSIONS)
    public void runAfterGetRW() {
        Log.v(TAG, "runAfterGetRW");
    }

    @AfterPermissionGranted(REQUEST_READ_WRITE_PERMISSIONS)
    public void runAfterGetRW1() {
        Log.v(TAG, "runAfterGetRW1");
    }

    @AfterPermissionGranted(REQUEST_FINE_LOCATION_PERMISSION)
    public void runAfterGetLocation() {
        Log.v(TAG, "runAfterGetLocation");
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.v(TAG, "onPermissionsGranted, requestCode: " + requestCode);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.v(TAG, "onPermissionsDenied, requestCode: " + requestCode);
        if (requestCode == REQUEST_READ_WRITE_PERMISSIONS) {
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                Log.w(TAG, "onDenied forever");
            }
        }
    }
}
