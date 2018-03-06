package com.a256.fortune256.viewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.a256.fortune256.R;
import com.a256.fortune256.activity.MainActivity;
import com.a256.fortune256.customer.TextImageView;
import com.a256.fortune256.debug.T;
import com.a256.fortune256.net.DataRequester;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by de165 on 2018/2/9.
 */

public class HomePage implements View.OnClickListener{
    private Handler mHandler = new Handler();
    private Context context;
    private View view;
    private ImageView newer,next;
    private TextImageView chongqing,sanfen,xingyun28,home_xglhc;
    private WebView webView;
    public View init(Context context){
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.view = inflater.inflate(R.layout.home,null);
        initView();
        initImage();
        return this.view;
    }
    private void initView(){
        newer = this.view.findViewById(R.id.home_newer);
        next = this.view.findViewById(R.id.home_next);

        chongqing = this.view.findViewById(R.id.home_cqssc);
        sanfen = this.view.findViewById(R.id.home_sfssc);
        xingyun28 = this.view.findViewById(R.id.home_xy28);
        home_xglhc = this.view.findViewById(R.id.home_xglhc);
        webView = this.view.findViewById(R.id.home_webview);

        chongqing.setOnClickListener(this);
        sanfen.setOnClickListener(this);
        xingyun28.setOnClickListener(this);
        home_xglhc.setOnClickListener(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.loadUrl("file:////android_res/raw/item.html");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                T.i("onReceivedError()"+error.toString());
            }
        });


    }
    private void initImage(){
        new Thread(){
            @Override
            public void run() {
                JsonArray array = (JsonArray) DataRequester.getNewest();
                JsonObject jsonNewest = array.get(1).getAsJsonObject();
                final String openCode = jsonNewest.get("openCode").toString().replace("\"","");

                final JsonObject jsonNext = array.get(0).getAsJsonObject();
                final long timestamp = Long.parseLong(jsonNext.get("timestamp").toString()) - Long.parseLong(jsonNewest.get("timestamp").toString());


                mHandler.post(new Thread(){
                    @Override
                    public void run() {
                        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
                        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
                        newer.measure(w,h);
                        next.measure(w,h);
                        newer.setImageBitmap(drawNewest(openCode,newer.getMeasuredWidth(),newer.getMeasuredHeight()));
                        next.setImageBitmap(drawNext(timestamp,next.getMeasuredWidth(),next.getMeasuredHeight()));
                    }
                });
            }
        }.start();
    }
    private Bitmap drawNewest(String numbers,int width, int height){
        Bitmap bitmap = null;
        String[] num = numbers.split(",");
        int len = num.length;
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();

        float cx = width / (len + 1);
        float cy = height / 2;

        for(int i=0;i<len;i++){
            float rx = cx * (i+1);
            // 画圆
            /*
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            //Log.i("--------->", "drawNewest: " + "X:"+cx * (i+1)+" Y:"+cy);
            canvas.drawCircle(rx,cy,52,paint);
            */

            //画数字
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(80);

            canvas.drawText(num[i],rx - paint.measureText(num[i])/2,cy*4/3,paint);

        }

        return bitmap;
    }
    private Bitmap drawNext(long time,int width, int height){
        if(time <= 0){
            time = -time;
        }
        long days = time/(60*60*24);
        long hour = (time - days *(60*60*24))/(60*60);
        long min = (time - days *(60*60*24) - hour * 3600)/60;
        String str = ((hour<10)?"0"+hour:hour)+":"+((min<10)?"0"+min:min);
        Bitmap bitmap = null;
        bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#1296DB"));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(180);
        float cx = width / 2;
        float cy = 3 * height / 4;
        canvas.drawText(str,cx - paint.measureText(str)/2,cy,paint);
        return bitmap;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.home_cqssc:
                //Toast.makeText(context, "重庆时时彩", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_sfssc:
                //Toast.makeText(context, "三分时时彩", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_xy28:
                //Toast.makeText(context, "幸运28", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_xglhc:
                //Toast.makeText(context, "香港六合彩", Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
    }
}
