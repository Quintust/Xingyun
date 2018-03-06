package com.a256.fortune256.customer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a256.fortune256.R;

/**
 * Created by de165 on 2018/2/26.
 */

public class TextImageView extends LinearLayout {
    private ImageView imageView = null;
    private TextView textView = null;
    public TextImageView(Context context) {
        super(context);
        init(context,null);
    }

    public TextImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void setImage(@Nullable int resId){
        imageView.setImageResource(resId);
    }

    public void setImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    public void setTextColor(int color){
        textView.setTextColor(color);
    }

    public void setTextSize(float size){
        textView.setTextSize(size);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setText(int resId){
        textView.setText(resId);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        imageView = new ImageView(context);
        textView = new TextView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextImageView);

        String text = typedArray.getString(R.styleable.TextImageView_text);
        int textColor = typedArray.getColor(R.styleable.TextImageView_textColor,Color.BLACK);
        float textSize = typedArray.getFloat(R.styleable.TextImageView_textSize,16);

        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setTextSize(textSize);

        Drawable imgSrc = typedArray.getDrawable(R.styleable.TextImageView_imgSrc);
        imageView.setImageDrawable(imgSrc);

        this.setPadding(50,0,0,0);
        this.setGravity(Gravity.CENTER);

        LayoutParams lp = new LayoutParams(0,LayoutParams.MATCH_PARENT,3);
        this.addView(imageView,lp);
        LayoutParams lp2 = new LayoutParams(0,LayoutParams.MATCH_PARENT,7);
        this.addView(textView,lp2);

        typedArray.recycle();
    }
}
