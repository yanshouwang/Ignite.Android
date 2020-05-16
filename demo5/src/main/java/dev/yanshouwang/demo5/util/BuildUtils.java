package dev.yanshouwang.demo5.util;

import android.annotation.SuppressLint;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BuildUtils {
    @SuppressWarnings({"unchecked", "JavaReflectionMemberAccess"})
    public static String getString(String property) {
        String value = Build.UNKNOWN;
        try {
            Class buildClass = Build.class;
            @SuppressLint("DiscouragedPrivateApi")
            Method method = buildClass.getDeclaredMethod("getString", String.class);
            method.setAccessible(true);
            value = (String) method.invoke(null, property);
            method.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean isXiaomi() {
        return Build.MANUFACTURER.equals("Xiaomi");
    }
}
