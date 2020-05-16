package dev.yanshouwang.demo5.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class XiaomiUtils {
    public static int getOSVersion() {
        String incrementalStr = Build.VERSION.INCREMENTAL.replace(".", "");
        return Integer.parseInt(incrementalStr);
    }

    @SuppressWarnings("JavaReflectionMemberAccess")
    public static void changeStyle(Activity activity, boolean light) {
        Class<? extends Window> windowClass = Window.class;
        try {
            @SuppressLint("PrivateApi")
            Class layoutParamsClass = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParamsClass.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int flag = field.getInt(layoutParamsClass);
            Method method = windowClass.getMethod("setExtraFlags", int.class, int.class);
            Window window = activity.getWindow();
            int mode = light ? flag : 0;
            method.invoke(window, mode, flag);
        } catch (ClassNotFoundException |
                NoSuchFieldException |
                IllegalAccessException |
                NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
