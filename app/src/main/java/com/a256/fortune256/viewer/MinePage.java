package com.a256.fortune256.viewer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.a256.fortune256.R;
import com.a256.fortune256.activity.LoginActivity;
import com.a256.fortune256.adapter.MyListViewAdapter;
import com.a256.fortune256.data.ListData;
import com.a256.fortune256.net.DataRequester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by de165 on 2018/2/5.
 */

public class MinePage implements View.OnClickListener{
    private RelativeLayout mine_funcs_settings;
    private ListView mListView;
    private Intent mIntent;
    private Context mContext;
    private View mView;
    public MinePage() {
        super();
    }
    public View init(Context context){
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.mView = inflater.inflate(R.layout.mine,null);
        initListener();
        return this.mView;
    }

    public void initListener(){
        mine_funcs_settings = this.mView.findViewById(R.id.mine_funcs_settings);
        mine_funcs_settings.setOnClickListener(this);
        mListView = this.mView.findViewById(R.id.mine_list_view);
        List<ListData> list = new ArrayList<>();
        list.add(new ListData(R.drawable.about,"关于我们"));
        MyListViewAdapter adapter = new MyListViewAdapter(this.mContext, list);
        adapter.setOnClickListener(new MyListViewAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int position) {
                switch (position){
                    case 0:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                        dialog.setTitle("关于我们");
                        dialog.setMessage("TEL:4000-888-400.");
                        dialog.show();
                        break;
                    default:
                        break;
                }
            }
        });
        mListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.mine_funcs_settings:
                mIntent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(mIntent);
                break;
            default:break;
        }
    }
}
