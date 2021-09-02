package hdvideoprojector.videosimulator.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import hdvideoprojector.videosimulator.R;


public class StartActivity extends AppCompatActivity {
    // Permission
    private boolean SettingDialogShow = false, UserGoneToSetting = false;
    private boolean UserClickOnSetting = false;

    TextView tvStartProject;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_start);

        bindView();
        addListner();

    }

    private void bindView() {
        tvStartProject = findViewById(R.id.tvStartProject);
    }

    private void addListner() {

        tvStartProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!SettingDialogShow || UserGoneToSetting) {
                UserGoneToSetting = false;
                UserClickOnSetting = false;
                getAllPermissions(false);
            }
        } else {
//            MyApplication.getInstance().getFolderList();
        }
        if (UserClickOnSetting) {
            UserGoneToSetting = true;
        }
    }

    public void getAllPermissions(final boolean NeverAskAgain) {
        final String[] NECESSARY_PERMISSIONS = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if ((ContextCompat.checkSelfPermission(StartActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
//            MyApplication.getInstance().getFolderList();
        } else {
            if (NeverAskAgain) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                        StartActivity.this);
                builder.setTitle("Necessary permission");
                builder.setCancelable(false);
                builder.setMessage("Allow Required Permission");
                builder.setPositiveButton("settings",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                goToSettings();
                            }
                        });
                builder.setNegativeButton(
                        "Exit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                finishAffinity();

                            }
                        });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(StartActivity.this,
                        NECESSARY_PERMISSIONS, 123);
            }
        }
    }

    private void goToSettings() {
        UserClickOnSetting = true;
        Intent myAppSettings = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(myAppSettings, 111);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0) {
                    if ((grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                        MyApplication.getInstance().getFolderList();
                    } else {
                        if ((grantResults[0] == PackageManager.PERMISSION_DENIED && !ActivityCompat
                                .shouldShowRequestPermissionRationale(
                                        StartActivity.this,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
                            SettingDialogShow = true;
                            getAllPermissions(true);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(StartActivity.this, "Exit", Toast.LENGTH_SHORT).show();
        finishAffinity();
    }




}