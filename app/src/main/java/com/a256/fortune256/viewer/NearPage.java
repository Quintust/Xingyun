package com.a256.fortune256.viewer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.a256.fortune256.R;

/**
 * Created by de165 on 2018/4/7.
 */

public class NearPage {

    private ListView mListView;
    private Intent mIntent;
    private Context mContext;
    private View mView;

    public NearPage() {
        super();
    }
    public View init(Context context){
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.mView = inflater.inflate(R.layout.near,null);
        return this.mView;
    }
}
