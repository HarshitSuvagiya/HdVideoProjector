package hdvideoprojector.videosimulator.utils;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ViraniShare extends AppCompatActivity {
    public static Activity activity;

    @Override // androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback, androidx.fragment.app.FragmentActivity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2].equals("android.permission.READ_EXTERNAL_STORAGE") && iArr[i2] == -1) {
                requestPermissions();
                return;
            }
        }
    }

    public static void requestPermissions() {
        List<String> checkPermissions = checkPermissions();
        if (checkPermissions.size() > 0) {
            ActivityCompat.requestPermissions(activity, (String[]) checkPermissions.toArray(new String[checkPermissions.size()]), 0);
        }
    }

    public static boolean isPermissionGranted(Activity activity2) {
        activity =  activity2;
        if (checkPermissions().size() == 0) {
            return true;
        }
        requestPermissions();
        return false;
    }

    public static List<String> checkPermissions() {
        ArrayList arrayList = new ArrayList();
        if (ContextCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        return arrayList;
    }
}
