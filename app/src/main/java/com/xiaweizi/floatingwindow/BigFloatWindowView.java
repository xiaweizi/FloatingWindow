package com.xiaweizi.floatingwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.floatingwindow.BigFloatWindowView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/12/15
 *     desc   :
 * </pre>
 */

public class BigFloatWindowView extends View {
    public BigFloatWindowView(Context context) {
        this(context, null);
    }

    public BigFloatWindowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BigFloatWindowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {

    }
}
