package hdvideoprojector.videosimulator.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;

public class FontChanger {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void overrideDefaultFont(Context context,
                                           String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void replaceFont(String staticTypefaceFieldName,
                                    final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
