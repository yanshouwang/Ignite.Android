package dev.yanshouwang.demo5.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.RequiresApi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class ActivityUtils {
    private static final String TAG = ActivityUtils.class.getSimpleName();

    /**
     * 将目标 Activity 转为半透明状态
     *
     * @param activity 目标 Activity
     * @param listener 透明状态完成时的回调
     * @return <code>true</code> 表示 Window 处于不透明状态并且将要执行转为透明的操作
     * <code>false</code> 表示 Window 已经处于透明状态，无需执行操作
     */
    @SuppressWarnings({"JavaReflectionMemberAccess", "UnusedReturnValue"})
    public static boolean toTranslucent(Activity activity, ActivityListener listener) {
        if (activity.isTaskRoot())
            return false;
        boolean value;
        try {
            Class[] classes = Activity.class.getDeclaredClasses();
            Class listenerClass = null;
            for (Class c : classes) {
                if (c.getSimpleName().contains("TranslucentConversionListener")) {
                    listenerClass = c;
                    break;
                }
            }
            if (listenerClass == null) {
                return false;
            }
            ClassLoader loader = listenerClass.getClassLoader();
            Class[] interfaces = new Class[]{listenerClass};
            InvocationHandler handler = (proxy, method, args) -> {
                if (listener != null) {
                    boolean drawCompleted = (boolean) args[0];
                    listener.toTranslucentFinished(drawCompleted);
                }
                Log.i(TAG, TAG + " ALREADY " + System.currentTimeMillis() + " : " + args[0]);
                return null;
            };
            Object listenerObj = Proxy.newProxyInstance(loader, interfaces, handler);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                @SuppressLint("DiscouragedPrivateApi")
                Method method1 = Activity.class.getDeclaredMethod("getActivityOptions");
                method1.setAccessible(true);
                ActivityOptions options = (ActivityOptions) method1.invoke(activity);
                method1.setAccessible(false);
                Method method2 = Activity.class.getDeclaredMethod("convertToTranslucent", listenerClass, ActivityOptions.class);
                method2.setAccessible(true);
                value = (boolean) method2.invoke(activity, listenerObj, options);
                method2.setAccessible(false);
            } else {
                Method method = Activity.class.getDeclaredMethod("convertToTranslucent", listenerClass);
                method.setAccessible(true);
                value = (boolean) method.invoke(activity, listenerObj);
                method.setAccessible(false);
            }
            Log.i(TAG, TAG + " TO " + System.currentTimeMillis() + " : " + value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            value = false;
        }
        return value;
    }

    /**
     * 将目标 Activity 转为不透明状态
     *
     * @param activity 目标 Activity
     */
    @SuppressWarnings("JavaReflectionMemberAccess")
    public static void fromTranslucent(Activity activity) {
        if (activity.isTaskRoot())
            return;
        try {
            Method method = Activity.class.getDeclaredMethod("convertFromTranslucent");
            method.setAccessible(true);
            method.invoke(activity);
            method.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.i(TAG, TAG + " FROM " + System.currentTimeMillis());
    }

    /**
     * 改变目标 Activity 的界面样式
     * @param activity  目标 Activity
     * @param light <code>true</code> 表示进入浅色模式
     *              <code>false</code> 表示进入深色模式
     */
    public static void changeStyle(Activity activity, boolean light) {
        if (BuildUtils.isXiaomi() && XiaomiUtils.getOSVersion() < 7713) {
            // 兼容 7.7.13 版本前的小米
            XiaomiUtils.changeStyle(activity, light);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // TODO: 此处需要测试, 暂未找到手机
            /*if (BuildUtils.isXiaomi()) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }*/
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int visibility = decorView.getSystemUiVisibility();
            if (light) {
                visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                visibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(visibility);
        }
    }

    /**
     * 使目标 Activity 进入沉浸模式
     * @param activity 目标 Activity
     */
    public static void immerse(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int visibility = decorView.getSystemUiVisibility();
        visibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(visibility);
    }
}
