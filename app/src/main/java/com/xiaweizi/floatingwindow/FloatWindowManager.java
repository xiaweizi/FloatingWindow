package com.xiaweizi.floatingwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import static android.view.WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.floatingwindow.FloatWindowManager
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/12/15
 *     desc   :
 * </pre>
 */

public class FloatWindowManager implements SmallFloatWindowView.ISmallCallback {

    private static FloatWindowManager instance;
    private WindowManager mWindowManager;
    private SmallFloatWindowView smallView;
    private BigFloatWindowView bigView;
    private WindowManager.LayoutParams smallParams;
    private DisplayMetrics dm;
    private Context mContext;
    private int xPosition = 10;
    private int yPosition = 0;
    private int currentState = FloatWindowState.NON_WINDOW;

    private static class FloatWindowState {
        public static final int NON_WINDOW = 0;
        public static final int SMALL_WINDOW = 1;
        public static final int BIG_WINDOW = 2;
    }

    public static FloatWindowManager getInstance(Context context) {
        if (null == instance) {
            synchronized (FloatWindowManager.class) {
                if (null == instance) {
                    instance = new FloatWindowManager(context);
                }
            }
        }
        return instance;
    }

    public FloatWindowManager(Context context) {
        mContext = context;
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
    }

    private WindowManager getWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

    /**
     * 显示小悬浮窗
     */
    public void showSmallFloatWin() {
        if (smallView == null) {
            smallView = new SmallFloatWindowView(mContext, dm.density);
            smallParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    FLAG_NOT_FOCUSABLE | FLAG_ALT_FOCUSABLE_IM, PixelFormat.TRANSLUCENT);
            smallParams.gravity = Gravity.LEFT | Gravity.TOP;
            smallParams.x = (int) xPosition;
            smallParams.y = (int) (dm.heightPixels / 4 - yPosition);
            smallView.setWindowsParams(smallParams);
            smallView.setOnSmallCallback(this);
        }
        removeOldFloatWindow();
        getWindowManager().addView(smallView, smallParams);
        currentState = FloatWindowState.SMALL_WINDOW;
    }

    private void removeOldFloatWindow() {
        switch (currentState) {
            case FloatWindowState.SMALL_WINDOW:
                if (smallView == null) {
                    return;
                }
                getWindowManager().removeView(smallView);
                break;
            case FloatWindowState.BIG_WINDOW:
                if (bigView == null) {
                    return;
                }
                getWindowManager().removeView(bigView);
                break;
        }
    }


    @Override
    public void onSmallWindowClick() {
        Toast.makeText(mContext, "gaga", Toast.LENGTH_SHORT).show();
    }
}
