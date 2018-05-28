package net.dmceu.booneu.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by vputtapan on 2/22/2018.
 */
@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        initTypFace(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypFace(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTypFace(attrs);
    }

    private void initTypFace(AttributeSet attrs) {
        if (isInEditMode() && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        int attrTextStyle = Typeface.NORMAL;
        if (attrs != null) {
            int[] attributes = new int[]{android.R.attr.textStyle};
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, attributes);
            attrTextStyle = typedArray.getInt(0, 0);
            typedArray.recycle();
        }

        String fontPath = "fonts/DB-Helvethaica-Regular.ttf";
        Typeface typeface = getTypeface();
        if (typeface != null) {
            if ((typeface.getStyle() & Typeface.BOLD_ITALIC) == Typeface.BOLD_ITALIC ||
                    (attrTextStyle & Typeface.BOLD_ITALIC) == Typeface.BOLD_ITALIC) {
                fontPath = "fonts/DB-Helvethaica-Regular.ttf";
            } else if ((typeface.getStyle() & Typeface.ITALIC) == Typeface.ITALIC ||
                    (attrTextStyle & Typeface.ITALIC) == Typeface.ITALIC) {
                fontPath = "fonts/DB-Helvethaica-Italic.ttf";
            } else if ((typeface.getStyle() & Typeface.BOLD) == Typeface.BOLD ||
                    (attrTextStyle & Typeface.BOLD) == Typeface.BOLD) {
                fontPath = "fonts/DB-Helvethaica-Bold.ttf";
            }
        }
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), fontPath));
    }
}
